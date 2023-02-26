package com.vinsguru.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author debal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    private String id;
    private String description;
    private int price;
}
