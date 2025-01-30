package com.wmscode.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
    
    @Schema(description = "Nome completo", example = "Pedido #1")
	@NotBlank(message = "Nome é obrigatório")
	@Length(max = 100)
    String name,

    @JsonDeserialize(as = DeliveryAddressRequest.class)
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
    @NotEmpty
    @NotNull
    @JsonDeserialize(as = ItemOrderRequest.class)
    @NotNull(message = "Pelo menos um item é obrigatório")
    List<ItemOrderRequest> itemOrderRequest,
	
    @Schema(description = "Total do pedido", example = "35999.69")
	@NotNull(message = "Total é obrigatório")
	Double total
) {}
