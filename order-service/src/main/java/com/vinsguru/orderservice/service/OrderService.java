package com.vinsguru.orderservice.service;

import static com.vinsguru.orderservice.dto.RequestContextBuilder.aRequestContext;
import static com.vinsguru.orderservice.dto.RequestContextBuilder.from;
import static com.vinsguru.orderservice.util.MappingUtil.toDto;
import static com.vinsguru.orderservice.util.MappingUtil.toEntity;
import com.vinsguru.orderservice.client.ProductClient;
import com.vinsguru.orderservice.client.UserClient;
import com.vinsguru.orderservice.dto.ProductDto;
import com.vinsguru.orderservice.dto.PurchaseOrderRequestDto;
import com.vinsguru.orderservice.dto.PurchaseOrderResponseDto;
import com.vinsguru.orderservice.dto.RequestContext;
import com.vinsguru.orderservice.dto.TransactionRequestDto;
import com.vinsguru.orderservice.dto.TransactionResponseDto;
import com.vinsguru.orderservice.entity.PurchaseOrder;
import com.vinsguru.orderservice.repository.PurchaseOrderRepository;
import com.vinsguru.orderservice.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author debal
 */
@Service
public class OrderService {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public Flux<PurchaseOrderResponseDto> productsByUserId(int userId) {
        return Flux.fromStream(() -> purchaseOrderRepository.findByUserId(userId).stream())
                .map(MappingUtil::toDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> purchaseOrderRequestDto) {
        final Function<RequestContext, PurchaseOrder> toEntity = MappingUtil::toEntity;
        final Function<PurchaseOrder, PurchaseOrderResponseDto> toDto = MappingUtil::toDto;
        final UnaryOperator<PurchaseOrder> save = purchaseOrderRepository::save;
        final Function<RequestContext, Function<ProductDto, RequestContext>> fromProductAndContext = ctx -> product -> aRequestContext()
                .productDto(product)
                .purchaseOrderRequestDto(ctx.getPurchaseOrderRequestDto())
                .build();
        final Function<RequestContext, Function<TransactionResponseDto, RequestContext>> transactionResponseToContext = ctx -> resp -> from(ctx)
                .transactionResponseDto(resp)
                .build();
        final UnaryOperator<RequestContext> makeTxnRequestDto = ctx -> from(ctx)
                .transactionRequestDto(new TransactionRequestDto(ctx.getPurchaseOrderRequestDto().getUserId(), ctx.getProductDto().getPrice()))
                .build();

        return purchaseOrderRequestDto
                .map(p -> aRequestContext().purchaseOrderRequestDto(p).build())
                .flatMap(reqCtx -> productClient.getProductById(reqCtx.getPurchaseOrderRequestDto().getProductId())
                        .retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(5))) // Resiliency by retry
                        .map(fromProductAndContext.apply(reqCtx)))
                .map(makeTxnRequestDto)
                .flatMap(reqCtx -> userClient.authorizeTransaction(reqCtx.getTransactionRequestDto()).map(transactionResponseToContext.apply(reqCtx)))
                .map(toEntity.andThen(save).andThen(toDto))// As the save is blocking, hence the following subscription is required!!
                .subscribeOn(Schedulers.boundedElastic());

    }
}
