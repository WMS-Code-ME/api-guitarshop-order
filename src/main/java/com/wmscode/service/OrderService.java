package com.wmscode.service;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wmscode.commons.exception.OrderNotFoundException;
import com.wmscode.model.request.OrderRequest;
import com.wmscode.model.request.UpdateOrderRequest;
import com.wmscode.repository.OrderDocument;
import com.wmscode.repository.OrderRepository;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestScoped
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private static final String HEX_PATTERN_ID = "^[0-9a-fA-F]{24}$";

    public String create(OrderRequest orderRequest) {
        
        if (orderRequest == null) {
            throw new OrderNotFoundException("Pedido não pode ser nulo: " + orderRequest);
        }

        log.info("Criando um novo pedido: {}", orderRequest.name());

        var items = orderRequest.itemOrderRequests().stream().toList();

        OrderDocument document = OrderDocument.builder()
            .customerId(orderRequest.customerId())
            .customerName(orderRequest.customerName())
            .name(orderRequest.name())
            .status(orderRequest.status())
            .total(orderRequest.total())
            .deliveryAddress(orderRequest.deliveryAddress())
            .itemOrderRequests(items)
            .build();
        
        repository.persist(document);

        return Optional.ofNullable(document.getId().toString()).orElse("-");
    }


    public String updateOrder(UpdateOrderRequest request) {
        if (!isValidHex(HEX_PATTERN_ID, request.orderId()) || isNull(request.orderId())){
            throw new OrderNotFoundException("ID do pedido inválido");
        }
        Optional
            .ofNullable(repository.findByIdOrder(request.orderId()))
            .map(document -> {
                document.setCustomerId(request.orderRequest().customerId());
                document.setCustomerName(request.orderRequest().customerName());
                document.setName(request.orderRequest().name());
                document.setStatus(request.orderRequest().status());
                document.setTotal(request.orderRequest().total());
                document.setDeliveryAddress(request.orderRequest().deliveryAddress());
                var items = request.orderRequest().itemOrderRequests().stream().toList();
                document.setItemOrderRequests(items);
                repository.persistOrUpdate(document);
                return document;
            })
            .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));

        return request.orderId();
    }

    public Object updateOrderAttributes(String id,
        OrderRequest orderRequest) {
            if (!isValidHex(HEX_PATTERN_ID, id) || isNull(id)){
                throw new OrderNotFoundException("ID do pedido inválido");
            }
            Optional
                .ofNullable(repository.findByIdOrder(id))
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
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));
    
            return id;
    }

    public OrderDocument getOrderById(String id) {
        if (!isValidHex(HEX_PATTERN_ID, id) || isNull(id)){
            throw new OrderNotFoundException("ID do pedido inválido");
        }

        return Optional
            .ofNullable(repository.findByIdOrder(id))
            .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));
    }

    public List<OrderDocument> getOrders() {
        return Optional
            .ofNullable(repository.findAll().list())
            .orElseThrow(() -> new OrderNotFoundException("Não existem pedidos"));
    }

    public Object countOrders() {
        return Optional
            .ofNullable(repository.count())
            .orElseThrow(() -> new OrderNotFoundException("Não existem pedidos"));
    }

    public OrderDocument getOrderByName(String name) {
        if (isNull(name)){
            throw new OrderNotFoundException("Nome do pedido inválido");
        }

        return (OrderDocument) Optional
            .ofNullable(repository.findByName(name))
            .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));
    }

    public String deleteOrder(String id) {
        if (!isValidHex(HEX_PATTERN_ID, id) || isNull(id)){
            throw new OrderNotFoundException("ID do pedido inválido");
        }

        return Optional
            .ofNullable(repository.findByIdOrder(id)).map(document -> {
                repository.delete(document);
                return document.getId().toString();
            })
            .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado: " + id));
    }

    private static boolean isValidHex(String hexPattern, String input) {
        Pattern pattern = Pattern.compile(hexPattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
