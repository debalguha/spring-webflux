package com.vinsguru.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author debal
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private int userId;
    private int amount;
    private TransactionStatus transactionStatus;
}
