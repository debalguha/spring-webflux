package com.vinsguru.orderservice.util;

import com.vinsguru.orderservice.dto.OrderStatus;
import com.vinsguru.orderservice.dto.PurchaseOrderResponseDto;
import com.vinsguru.orderservice.dto.RequestContext;
import com.vinsguru.orderservice.entity.PurchaseOrder;

/**
 * @author debal
 */
public class MappingUtil {
    public static PurchaseOrder toEntity(RequestContext ctx) {
        return new PurchaseOrder(null, ctx.getPurchaseOrderRequestDto().getUserId(), ctx.getPurchaseOrderRequestDto().getProductId(),
                ctx.getTransactionResponseDto().getAmount(), OrderStatus.from(ctx.getTransactionResponseDto().getTransactionStatus()));
    }

    public static PurchaseOrderResponseDto toDto(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderResponseDto(purchaseOrder.getUserId(), purchaseOrder.getProductId(), purchaseOrder.getId(), purchaseOrder.getAmount(), purchaseOrder.getOrderStatus());
    }
}

