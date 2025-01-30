package com.wmscode.model;

import java.util.List;

import com.wmscode.util.StatusOrderEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Order {
    private String id;
    private String name;
    private DeliveryAddress deliveryAddress;
    private String customerName;
    private String customerId;
    private List<ItemOrder> itemOrder;
    private Double total;
    private StatusOrderEnum status;
}
