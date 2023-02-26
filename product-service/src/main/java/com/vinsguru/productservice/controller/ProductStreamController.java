package com.vinsguru.productservice.controller;

import com.vinsguru.productservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author debal
 */
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:8091")
public class ProductStreamController {

    @Autowired
    private Flux<ProductDto> productDtoFlux;

    @GetMapping(value = "stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> stream(@PathVariable("maxPrice") int maxPrice) {
        return productDtoFlux.filter(dto -> dto.getPrice() <=maxPrice );
    }
}
