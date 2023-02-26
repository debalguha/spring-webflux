package com.vinsguru.productservice.service;

import com.vinsguru.productservice.dto.ProductDto;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.time.Duration;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author debal
 */
@Service
public class DataSetupService implements CommandLineRunner {
    @Autowired
    private ProductService productService;
    final PodamFactory podamFactory = new PodamFactoryImpl();
    @Override
    public void run(String... args) throws Exception {

        final IntSupplier price = () -> RandomUtils.nextInt(1, 800);
        Flux.range(1, 10)
                .map(i -> podamFactory.manufacturePojo(ProductDto.class).updateId(String.valueOf(i)).updatePrice(price.getAsInt()))
                .concatWith(productEmitter())
                .map(Mono::just)
                .flatMap(productService::insertProduct)
                .subscribe(System.out::println);


    }

    private Flux<ProductDto> productEmitter() {
        final IntSupplier price = () -> RandomUtils.nextInt(1, 8000);
        return Flux.range(11, 1000)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> podamFactory.manufacturePojo(ProductDto.class).updateId(String.valueOf(i)).updatePrice(price.getAsInt()));

    }
}
