package com.vinsguru.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author debal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private String id;
    private String description;
    private int price;

    public ProductDto updateId(String id) {
        this.id = id;
        return this;
    }

    public ProductDto updatePrice(Integer input) {
        this.setPrice(input);
        return this;
    }
}
