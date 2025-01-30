package com.wmscode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemOrder {
    private String id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Double total;
}
