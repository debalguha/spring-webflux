package com.vinsguru.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class RequestContext {
    private PurchaseOrderRequestDto purchaseOrderRequestDto;
    private ProductDto productDto;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;


}
