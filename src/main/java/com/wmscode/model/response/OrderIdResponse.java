package com.wmscode.model.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record OrderIdResponse(
    @Schema(description = "Identificador do pedido", example = "64061fb97415c10585f94482")
    String orderId
) {}
