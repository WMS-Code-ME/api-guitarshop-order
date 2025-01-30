package com.wmscode.model.response;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.wmscode.model.ItemOrder;
import com.wmscode.util.StatusOrderEnum;

public record OrderResponse(
    @Schema(description = "Identificador do pedido", example = "64061fb97415c10585f94482") String id,
    @Schema(description = "Nome do cliente", example = "Pedido #1") String name,
    @Schema(description = "Endereço de entrega") DeliveryAddressResponse deliveryAddress,
    @Schema(description = "Nome do cliente", example = "Fulano de Tal") String cliente,
    @Schema(description = "Total do pedido", example = "10000.99") Double total,
    @Schema(description = "Lista de itens do pedido de compra") List<ItemOrder> itemOrder,
    @Schema(description = "Status do pedido de compra", example = "CONFIRMED") StatusOrderEnum status
) {}
