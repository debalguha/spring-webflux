package com.vinsguru.orderservice.entity;

import com.vinsguru.orderservice.dto.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author debal
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private int userId;
    private String productId;
    private int amount;

    private OrderStatus orderStatus;

}
