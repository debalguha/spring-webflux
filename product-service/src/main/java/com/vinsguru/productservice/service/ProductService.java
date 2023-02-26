package com.vinsguru.productservice.service;

import com.vinsguru.productservice.dto.ProductDto;
import com.vinsguru.productservice.repository.ProductRepository;
import com.vinsguru.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/**
 * @author debal
 */
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private Sinks.Many<ProductDto> sink;

    public Flux<ProductDto> all() {
        return productRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> findById(String id) {
        return productRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> dto) {
        return dto
                .map(EntityDtoUtil::toEntity)
                .flatMap(productRepository::insert)
                .map(EntityDtoUtil::toDto)
                .doOnNext(productDto -> sink.tryEmitNext(productDto));

    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> dto) {

        return findById(id)
                .flatMap(p -> dto.map(EntityDtoUtil::toEntity).doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(EntityDtoUtil::toDto);

    }

    public Mono<Void> deleteProduct(String id) {
        return this.productRepository.deleteById(id);
    }


}
