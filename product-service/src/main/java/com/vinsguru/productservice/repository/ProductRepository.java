package com.vinsguru.productservice.repository;

import com.vinsguru.productservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author debal
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
