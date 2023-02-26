package com.vinsguru.orderservice.dto;

/**
 * @author debal
 */
public enum OrderStatus {
    COMPLETED, FAILED;
    public static OrderStatus from(TransactionStatus transactionStatus) {
        switch (transactionStatus) {
            case APPROVED: return COMPLETED;
            default: return FAILED;
        }
    }
}
