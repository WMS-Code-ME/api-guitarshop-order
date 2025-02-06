package com.wmscode.order.service;

import java.util.List;
import java.util.Optional;

import com.wmscode.commons.exception.OrderNotFoundException;
import com.wmscode.order.model.DeliveryAddressDTO;
import com.wmscode.order.model.ItemOrderDTO;
import com.wmscode.order.model.request.DeliveryAddressRequest;
import com.wmscode.order.model.request.ItemOrderRequest;
import com.wmscode.order.model.request.OrderRequest;
import com.wmscode.order.model.request.UpdateOrderRequest;
import com.wmscode.order.model.response.OrderIdResponse;
import com.wmscode.order.model.response.OrderResponse;
import com.wmscode.order.repository.OrderRepository;
import com.wmscode.order.repository.mongodb.document.OrderDocument;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestScoped
@RequiredArgsConstructor
public class OrderService {

    private static final String ORDER_NOT_FOUND_MESSAGE = "Pedido não encontrado ";
    private final OrderRepository repository;

    public OrderIdResponse create(OrderRequest orderRequest) {

        valiOrderRequest(orderRequest);
        
        var order = buildOrderDocument(orderRequest);
        repository.persist(order);

        return buildOrderIdResponse(order.getId().toString());
    }

    public OrderIdResponse updateOrder(UpdateOrderRequest request) {
                
        validateUpdateOrderRequest(request);

        Optional
            .ofNullable(repository.findByIdOrder(request.id()))
            .map(document -> {
                document.setCustomerId(request.customerId());
                document.setCustomerName(request.customerName());
                document.setName(request.name());
                document.setStatus(request.status());
                document.setTotal(request.total());
                document.setDeliveryAddress(request.deliveryAddress());
                var items = request.itemOrderRequests().stream().toList();
                document.setItemOrderRequests(items);
                repository.persistOrUpdate(document);
                return document;
            })
            .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + request.id()));

        return buildOrderIdResponse(request.id());
    }

    public OrderIdResponse updateOrderAttributes(UpdateOrderRequest orderRequest) {
        Optional
            .ofNullable(repository.findByIdOrder(orderRequest.id()))
            .map(document -> {
                Optional.ofNullable(orderRequest.customerId()).ifPresent(document::setCustomerId);
                Optional.ofNullable(orderRequest.customerName()).ifPresent(document::setCustomerName);
                Optional.ofNullable(orderRequest.name()).ifPresent(document::setName);
                Optional.ofNullable(orderRequest.status()).ifPresent(document::setStatus);
                Optional.ofNullable(orderRequest.total()).ifPresent(document::setTotal);
                Optional.ofNullable(orderRequest.deliveryAddress()).ifPresent(document::setDeliveryAddress);
                Optional.ofNullable(orderRequest.itemOrderRequests()).ifPresent(document::setItemOrderRequests);
                repository.persistOrUpdate(document);
                return document;
            })
            .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + orderRequest.id()));

        return  buildOrderIdResponse(orderRequest.id());
    }

    public OrderResponse getOrderById(String id) {
        return Optional
            .ofNullable(repository.findByIdOrder(id))
            .map(this::buildOrderResponse)
            .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + id));
    }

    public List<OrderResponse> getOrders() {
        return repository.findAll().stream().map(this::buildOrderResponse).toList();
    }

    public Long countOrders() {
        return repository.count();
    }

    public OrderResponse getOrderByName(String name) {
        return Optional
            .ofNullable(repository.findByName(name))
            .map(this::buildOrderResponse)
            .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE    + name));
    }

    public void deleteOrder(String id) {
        Optional
            .ofNullable(repository.findByIdOrder(id)).map(document -> {
                repository.delete(document);
                return document.getId().toString();
            })
            .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_MESSAGE + id));
    }

    private UpdateOrderRequest validateUpdateOrderRequest(UpdateOrderRequest orderRequest) {
        return Optional
            .ofNullable(orderRequest).map(order -> {
                Optional.ofNullable(order.id().toString()).orElseThrow(() -> new OrderNotFoundException("ID do pedido é obrigatório"));
                Optional.ofNullable(order.name()).orElseThrow(() -> new OrderNotFoundException("Nome do pedido é obrigatório"));
                Optional.ofNullable(order.customerId()).orElseThrow(() -> new OrderNotFoundException("Identificador do cliente é obrigatório"));
                Optional.ofNullable(order.customerName()).orElseThrow(() -> new OrderNotFoundException("Nome do cliente é obrigatório"));
                Optional.ofNullable(order.deliveryAddress()).orElseThrow(() -> new OrderNotFoundException("Endereço de entrega é obrigatório"));
                Optional.ofNullable(order.itemOrderRequests()).orElseThrow(() -> new OrderNotFoundException("Pelo menos um item é obrigatório"));
                Optional.ofNullable(order.total()).orElseThrow(() -> new OrderNotFoundException("Total é obrigatório"));
                Optional.ofNullable(order.status()).orElseThrow(() -> new OrderNotFoundException("Status é obrigatório"));

                return order;
            })
            .orElseThrow(() -> new OrderNotFoundException("Pedido não pode ser nulo"));
    }

    private OrderRequest valiOrderRequest(OrderRequest request) {
        return Optional
            .ofNullable(request).map(order -> {
                Optional.ofNullable(order.name()).orElseThrow(() -> new OrderNotFoundException("Nome do pedido é obrigatório"));
                Optional.ofNullable(order.customerId()).orElseThrow(() -> new OrderNotFoundException("Identificador do cliente é obrigatório"));
                Optional.ofNullable(order.customerName()).orElseThrow(() -> new OrderNotFoundException("Nome do cliente é obrigatório"));
                Optional.ofNullable(order.deliveryAddress()).orElseThrow(() -> new OrderNotFoundException("Endereço de entrega é obrigatório"));
                Optional.ofNullable(order.itemOrderRequests()).orElseThrow(() -> new OrderNotFoundException("Pelo menos um item é obrigatório"));
                Optional.ofNullable(order.total()).orElseThrow(() -> new OrderNotFoundException("Total é obrigatório"));
                Optional.ofNullable(order.status()).orElseThrow(() -> new OrderNotFoundException("Status é obrigatório"));

                return order;
            })
            .orElseThrow(() -> new OrderNotFoundException("Pedido não pode ser nulo"));
    }

    private OrderResponse buildOrderResponse(OrderDocument document) {
        return OrderResponse.builder()
            .customerId(document.getCustomerId())
            .customerName(document.getCustomerName())
            .name(document.getName())
            .status(document.getStatus())
            .total(document.getTotal())
            .deliveryAddress(buildDeliveryAddress(document.getDeliveryAddress()))
            .itemOrder(buildItemOrder(document.getItemOrderRequests()))
            .id(document.getId().toString())
            .build();
    }

    private OrderDocument buildOrderDocument(OrderRequest orderRequest) {
        return OrderDocument.builder()
            .customerId(orderRequest.customerId())
            .customerName(orderRequest.customerName())
            .name(orderRequest.name())
            .status(orderRequest.status())
            .total(orderRequest.total())
            .deliveryAddress(orderRequest.deliveryAddress())
            .itemOrderRequests(orderRequest.itemOrderRequests().stream().toList())
            .build();
    }

    private DeliveryAddressDTO buildDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest) {
        return DeliveryAddressDTO.builder()
            .city(deliveryAddressRequest.city())
            .neighborhood(deliveryAddressRequest.neighborhood())
            .number(deliveryAddressRequest.number())
            .state(deliveryAddressRequest.state())
            .street(deliveryAddressRequest.street())
            .zipCode(deliveryAddressRequest.zipCode())
            .build();
    }

    private List<ItemOrderDTO> buildItemOrder(List<ItemOrderRequest> itemOrderRequests) {
        return itemOrderRequests.stream().map(item -> {
            return ItemOrderDTO.builder()
                .description(item.description())
                .price(item.price())
                .quantity(item.quantity())
                .build();
        }).toList();
    }

    private OrderIdResponse buildOrderIdResponse(String id) {
        return OrderIdResponse.builder().id(id).build();
    }
}
