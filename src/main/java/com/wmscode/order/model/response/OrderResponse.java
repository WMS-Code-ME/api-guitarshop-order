package com.wmscode.order.model.response;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.wmscode.commons.enums.StatusOrderEnum;
import com.wmscode.order.model.DeliveryAddress;
import com.wmscode.order.model.ItemOrder;

import lombok.Builder;

@Builder
public record OrderResponse(
    @Schema(description = "Identificador do pedido", example = "64061fb97415c10585f94482")
    String id,
    
    @Schema(description = "Descrição do pedido", example = "Pedido #1")
    String name,
    
    @Schema(description = "Endereço de entrega")
    DeliveryAddress deliveryAddress,
    
    @Schema(description = "Nome do cliente", example = "Nome Cliente")
    String customerName,
    
    @Schema(description = "Identificador do cliente", example = "64061fb97415c10585f94482")
    String customerId,
    
    @Schema(description = "Lista de itens do pedido")
    List<ItemOrder> itemOrder,

    @Schema(description = "Total do pedido", example = "35999.69")
    Double total,

    @Schema(description = "Status do pedido")
    StatusOrderEnum status
) { }
