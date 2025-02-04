package com.wmscode.order.controller.impl;

import com.wmscode.order.controller.OrderController;
import com.wmscode.order.model.request.OrderRequest;
import com.wmscode.order.model.request.UpdateOrderRequest;
import com.wmscode.order.service.OrderService;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static jakarta.ws.rs.core.Response.Status.CREATED;

@Slf4j
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override  
    public Response createOrder(OrderRequest request) {
        log.info("Cria um pedido: {}", request);

        return Response.status(CREATED)
            .entity(orderService.create(request)).build();
    }

    @Override
    public Response updateOrder(UpdateOrderRequest request) {
        log.info("Altera um pedido: {}", request.id());
        return Response.ok(orderService.updateOrder(request)).build();
    }

    @Override
    public Response updateOrderAttribute(UpdateOrderRequest updateOrderRequest) {
        log.info("Altera campos de um pedido: {}", updateOrderRequest.id());
        return Response.ok(orderService.updateOrderAttributes(updateOrderRequest)).build();
    }

    @Override
    public Response deleteOrder(String id) {
        log.info("Remove um pedido: {}", id);
        orderService.deleteOrder(id);
        return Response.ok().build();
    }

    @Override
    public Response getOrderById(String id) {
        log.info("Retorna um pedido pelo id: {}", id);
        return Response.ok(orderService.getOrderById(id)).build();
    }

    @Override
    public Response getOrders() {
        log.info("Retorna uma lista de pedidos");
        return Response.ok(orderService.getOrders()).build();
    }

    @Override
    public Response countOrders() {
        log.info("Retorna a quantidade de pedidos");
        return Response.ok(orderService.countOrders()).build();
    }

    @Override
    public Response getOrderByName(String query) {
        log.info("Retorna um pedido pelo nome: {}", query);
        return Response.ok(orderService.getOrderByName(query)).build();
    }
}
