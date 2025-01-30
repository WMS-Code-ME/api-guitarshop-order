package com.wmscode.model.response;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.wmscode.model.Order;

public record OrdersResponse(
    @Schema(description = "Lista de pedidos de compra")
        List<Order> orders
) {}