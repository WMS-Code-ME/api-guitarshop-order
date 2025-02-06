package com.wmscode.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemOrderDTO {
    private String id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Double total;
}
