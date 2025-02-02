package com.wmscode.model.request;


import java.util.List;

import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import com.wmscode.commons.enums.StatusOrderEnum;

import jakarta.validation.constraints.NotNull;

public record OrderRequest(
    
    @Schema(description = "Nome completo", example = "Pedido #1")
	@NotBlank(message = "Nome é obrigatório")
	@Length(max = 100)
    String name,

    @Schema(description = "Endereço de entrega")
    @NotNull(message = "Endereço de entrega é obrigatório")
    DeliveryAddressRequest deliveryAddress,
    
    @Schema(description = "Nome do cliente", example = "Nome Cliente")
    @NotNull(message = "Nome do cliente é obrigatório")
    String customerName,

    @Schema(description = "Identificador do cliente", example = "64061fb97415c10585f94482")
    @NotNull(message = "Identificador do cliente é obrigatório")
    String customerId,
    
    @Schema(description = "Lista de itens do pedido de compra")
    @NotNull(message = "Pelo menos um item é obrigatório")
    List<ItemOrderRequest> itemOrderRequests,
	
    @Schema(description = "Total do pedido", example = "35999.69")
	@NotNull(message = "Total é obrigatório")
	Double total,

    @Schema(description = "Status do pedido")
    @NotNull(message = "Total é obrigatório")
    StatusOrderEnum status
) {}
