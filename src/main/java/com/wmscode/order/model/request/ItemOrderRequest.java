package com.wmscode.order.model.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;

public record ItemOrderRequest(
    @Schema(description = "Nome do item", example = "Gibson Les Paul")
    @NotNull(message = "Nome do item é obrigatório")
    String name,
    @Schema(description = "Descrição do item", example = "Guitarra Custom Shop Gibson Les Paul 1970")
    @NotNull(message = "Descrição do item é obrigatório")
    String description,
    @Schema(description = "Quantidade", example = "1")
    @NotNull(message = "Quantidade do item é obrigatório")
    Integer quantity,
    @Schema(description = "Preço unitário do item", example = "35999.69")
    @NotNull(message = "Preço unitário do item é obrigatório")
    Double price,
    @Schema(description = "Total", example = "35999.69")
    @NotNull(message = "Total é obrigatório")
    Double total
) {}
