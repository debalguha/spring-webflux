package com.vinsguru.orderservice.dto;

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
public class PurchaseOrderRequestDto {
    private int userId;
    private String productId;
}
