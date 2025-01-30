package com.wmscode.model.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record DeliveryAddressResponse(    @Schema(description = "Endereço", example = "Avenida Paulista")
    String street,
    @Schema(description = "Número", example = "123B")
    String number,
    @Schema(description = "Bairro", example = "Centro")
    String neighborhood,
    @Schema(description = "Cidade", example = "São Paulo")
    String city,
    @Schema(description = "Estado/UF", example = "SP")
    String state,
    @Schema(description = "CEP", example = "02915020")
    String zipCode) {}
