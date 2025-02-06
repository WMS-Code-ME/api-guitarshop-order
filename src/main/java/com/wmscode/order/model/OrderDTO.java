package com.wmscode.order.model;

import java.util.List;

import com.wmscode.commons.enums.StatusOrderEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String id;
    private String name;
    private DeliveryAddressDTO deliveryAddress;
    private String customerName;
    private String customerId;
    private List<ItemOrderDTO> itemOrder;
    private Double total;
    private StatusOrderEnum status;
}
