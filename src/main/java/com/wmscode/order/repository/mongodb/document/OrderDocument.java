package com.wmscode.order.repository.mongodb.document;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import com.wmscode.commons.enums.StatusOrderEnum;
import com.wmscode.order.model.request.DeliveryAddressRequest;
import com.wmscode.order.model.request.ItemOrderRequest;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MongoEntity(collection = "orders")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDocument {
    @BsonId
    ObjectId id;
    private String name;
    private DeliveryAddressRequest deliveryAddress;
    private String customerName;
    private String customerId;
    private List<ItemOrderRequest> itemOrderRequests;
    private Double total;
    private StatusOrderEnum status;
}
