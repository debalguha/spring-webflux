package com.vinsguru.orderservice;

import com.vinsguru.orderservice.client.ProductClient;
import com.vinsguru.orderservice.client.UserClient;
import com.vinsguru.orderservice.dto.OrderStatus;
import com.vinsguru.orderservice.dto.PurchaseOrderRequestDto;
import com.vinsguru.orderservice.dto.PurchaseOrderResponseDto;
import com.vinsguru.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OrderServiceApplicationTests {

    @Autowired
    private UserClient userClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderService orderService;

	@Test
	void combinationOfOrders() {
        final Flux<PurchaseOrderResponseDto> purchaseOrderResponseDtoFlux = Flux.zip(userClient.allUsers(), productClient.getAllProducts())
                .map((t) -> Mono.just(new PurchaseOrderRequestDto(t.getT1().getId(), t.getT2().getId())))
                .flatMap(dto -> orderService.processOrder(dto))
                .doOnNext(System.out::println);

        StepVerifier.create(purchaseOrderResponseDtoFlux)
                .expectNextCount(4)
                .verifyComplete();

    }

    @Test
    void groupingOrderByStatus() {
        final Flux<GroupedFlux<OrderStatus, PurchaseOrderResponseDto>> groupedFlux = Flux.zip(userClient.allUsers(), productClient.getAllProducts())
                .map((t) -> Mono.just(new PurchaseOrderRequestDto(t.getT1().getId(), t.getT2().getId())))
                .flatMap(dto -> orderService.processOrder(dto))
                .doOnNext(System.out::println)
                .groupBy(PurchaseOrderResponseDto::getOrderStatus);

        StepVerifier.create(groupedFlux)
                .expectNextCount(2)
                .verifyComplete();

    }

}
