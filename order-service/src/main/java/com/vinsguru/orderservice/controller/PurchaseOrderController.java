package com.vinsguru.orderservice.controller;

import com.vinsguru.orderservice.dto.PurchaseOrderRequestDto;
import com.vinsguru.orderservice.dto.PurchaseOrderResponseDto;
import com.vinsguru.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author debal
 */
@RestController
@RequestMapping("order")
public class PurchaseOrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
        return orderService.processOrder(requestDtoMono)
                    .map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("user/{id}")
    public Flux<PurchaseOrderResponseDto> ordersByUser(@PathVariable("id") int userId) {
        return orderService.productsByUserId(userId);
    }

}
