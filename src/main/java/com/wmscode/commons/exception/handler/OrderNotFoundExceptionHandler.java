package com.wmscode.commons.exception.handler;

import java.util.Map;

import com.wmscode.commons.exception.OrderNotFoundException;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class OrderNotFoundExceptionHandler implements ExceptionMapper<OrderNotFoundException> {
    @Override
    public Response toResponse(OrderNotFoundException exception) {
        return Response.status(NOT_FOUND).entity(Map.of("message", exception.getMessage())).build();
    }

}
