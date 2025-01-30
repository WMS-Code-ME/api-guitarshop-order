package com.wmscode.controller.impl;

import com.wmscode.controller.OrderController;
import com.wmscode.model.request.OrderRequest;
import com.wmscode.model.request.UpdateOrderRequest;

import jakarta.ws.rs.core.Response;

public class OrderControllerImpl implements OrderController {

    @Override
    public Response createOrder(OrderRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public Response getOrderById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public Response getOrderByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderByName'");
    }

    @Override
    public Response getOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrders'");
    }


    @Override
    public Response deleteOrder(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public Response countOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countOrders'");
    }

    @Override
    public Response updateOrder(UpdateOrderRequest updateOrderRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

}
