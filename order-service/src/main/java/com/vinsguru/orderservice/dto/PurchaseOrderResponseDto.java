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
public class PurchaseOrderResponseDto {
    private int userId;
    private String productId;

    private int orderId;

    private int amount;

    private OrderStatus orderStatus;
}
