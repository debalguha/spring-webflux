package com.vinsguru.orderservice.dto;

/**
 * @author debal
 */
public final class RequestContextBuilder {
    private PurchaseOrderRequestDto purchaseOrderRequestDto;
    private ProductDto productDto;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;

    private RequestContextBuilder() {
    }

    public static RequestContextBuilder aRequestContext() {
        return new RequestContextBuilder();
    }

    public RequestContextBuilder purchaseOrderRequestDto(PurchaseOrderRequestDto purchaseOrderRequestDto) {
        this.purchaseOrderRequestDto = purchaseOrderRequestDto;
        return this;
    }

    public RequestContextBuilder productDto(ProductDto productDto) {
        this.productDto = productDto;
        return this;
    }

    public RequestContextBuilder transactionRequestDto(TransactionRequestDto transactionRequestDto) {
        this.transactionRequestDto = transactionRequestDto;
        return this;
    }

    public RequestContextBuilder transactionResponseDto(TransactionResponseDto transactionResponseDto) {
        this.transactionResponseDto = transactionResponseDto;
        return this;
    }

    public static RequestContextBuilder from(RequestContext otherContext) {
        return aRequestContext().purchaseOrderRequestDto(
                    otherContext.getPurchaseOrderRequestDto())
                    .productDto(otherContext.getProductDto())
                    .transactionRequestDto(otherContext.getTransactionRequestDto())
                    .transactionResponseDto(otherContext.getTransactionResponseDto()
                );
    }

    public RequestContext build() {
        RequestContext requestContext = new RequestContext();
        requestContext.setPurchaseOrderRequestDto(purchaseOrderRequestDto);
        requestContext.setProductDto(productDto);
        requestContext.setTransactionRequestDto(transactionRequestDto);
        requestContext.setTransactionResponseDto(transactionResponseDto);
        return requestContext;
    }
}
