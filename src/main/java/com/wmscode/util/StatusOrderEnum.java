package com.wmscode.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusOrderEnum {
    CONFIRMED("CONFIRMED", "Confirmado"),
    IN_PROGRESS("IN_PROGRESS", "Em andamento"),
    DELIVERED("DELIVERED", "Entregue"),
    CANCELED("CANCELED", "Cancelado");

    private final String status;
    private final String description;
}
