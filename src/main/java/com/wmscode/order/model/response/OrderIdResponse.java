package com.wmscode.order.model.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Builder;

@Builder
public record OrderIdResponse(
    @Schema(description = "Identificador do pedido", example = "64061fb97415c10585f94482")
    String id
) { }
