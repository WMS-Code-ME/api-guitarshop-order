package com.wmscode.model.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderRequest(

    @Schema(description = "Identificador do pedido", example = "64061fb97415c10585f94482")
    @NotNull(message = "Identificador do pedido é obrigatório")
    String orderId,
    
    @Schema(description = "Pedido de compra")
    @JsonSerialize(as = OrderRequest.class)
    @NotNull(message = "Pedido de compra é obrigatório")
    OrderRequest orderRequest
) {}
