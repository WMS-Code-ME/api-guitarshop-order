package com.wmscode.order.model.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;

public record DeliveryAddressRequest(
    @Schema(description = "Endereço", example = "Avenida Paulista")
    @NotNull(message = "Endereço é obrigatório")
    String street,
    @Schema(description = "Número", example = "123B")
    @NotNull(message = "Número é obrigatório")
    String number,
    @NotNull(message = "Bairro é obrigatório")
    @Schema(description = "Bairro", example = "Centro")
    String neighborhood,
    @Schema(description = "Cidade", example = "São Paulo")
    @NotNull(message = "Cidade é obrigatório")
    String city,
    @Schema(description = "Estado/UF", example = "SP")
    @NotNull(message = "Estado/UF é obrigatório")
    String state,
    @Schema(description = "CEP", example = "02915020")
    @NotNull(message = "CEP é obrigatório")
    String zipCode
) {}
