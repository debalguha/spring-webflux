package com.vinsguru.productservice.util;

import com.vinsguru.productservice.dto.ProductDto;
import com.vinsguru.productservice.entity.Product;

/**
 * @author debal
 */
public class EntityDtoUtil {

    public static ProductDto toDto(Product entity) {
        return new ProductDto(entity.getId(), entity.getDescription(), entity.getPrice());
    }

    public static Product toEntity(ProductDto dto) {
        return new Product(dto.getId(), dto.getDescription(), dto.getPrice());
    }
}
