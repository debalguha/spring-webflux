package com.vinsguru.productservice.controller;

import com.vinsguru.productservice.dto.ProductDto;
import com.vinsguru.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author debal
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public Flux<ProductDto> all(){
        return productService.all();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> byId(@PathVariable("id") String id){
        return productService.findById(id)
                .map(ResponseEntity::ok)
                //.defaultIfEmpty(ResponseEntity.noContent().build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> dtoMono) {
        return productService.insertProduct(dtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> update(@PathVariable("id") String id, @RequestBody Mono<ProductDto> dtoMono) {
        return productService.updateProduct(id, dtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("id")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }
}
