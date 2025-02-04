package com.wmscode.order.controller;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.ARRAY;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.wmscode.order.model.request.OrderRequest;
import com.wmscode.order.model.request.UpdateOrderRequest;
import com.wmscode.order.model.response.OrderIdResponse;
import com.wmscode.order.model.response.OrderResponse;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Tag(name = "Pedidos", description = "Pedidos de produtos da Guitar Shop")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/api/orders")
public interface OrderController {

    @POST
    @Operation(summary = "Criar pedido", description = "Cria um novo pedido de compra")
    @APIResponse(responseCode = "201", description = "CREATED", content = {
        @Content(
            mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = OrderIdResponse.class),
            examples = @ExampleObject(value = "true")
        )})
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    Response createOrder(OrderRequest request);

    @PUT
    @Operation(summary = "Altera um pedido de compra", description = "Altera um pedido de compra")
    @APIResponse(responseCode = "200", description = "SUCCESS", content = {
        @Content(
            mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = OrderIdResponse.class),
            examples = @ExampleObject(value = "true")
        )}) 
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    Response updateOrder(UpdateOrderRequest updateOrderRequest);

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Altera campos de um pedido de compra", description = "Altera campos de um pedido de compra")
    @APIResponse(responseCode = "200", description = "SUCCESS", content = {
        @Content(
            mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = OrderIdResponse.class),
            examples = @ExampleObject(value = "true")
        )}) 
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    Response updateOrderAttribute(UpdateOrderRequest updateOrderRequest);

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remove um pedido de compra", description = "Remove um pedido de compra pelo identificador")
    @APIResponse(
        responseCode = "200", description = "SUCCESS", content = {
            @Content(
                mediaType = APPLICATION_JSON,
                schema = @Schema(implementation = OrderIdResponse.class),
                examples = @ExampleObject(value = "true")
            )}
        
    ) 
    @APIResponse(
		responseCode = "404", description = "NOT FOUND"
	)
    @APIResponse(
		responseCode = "400", description = "BAD REQUEST"
	)
    Response deleteOrder(@PathParam("id") String id);
    
    @GET
	@Operation(summary = "Lista pedidos de compra", description = "Retorna uma lista de pedidos de compra")
	@APIResponse(
		responseCode = "200", description = "SUCCESS", content = {
            @Content(
                mediaType = APPLICATION_JSON,
                schema = @Schema(implementation = OrderResponse.class, type = ARRAY),
                examples = @ExampleObject(value = "true")
            )}
	)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    Response getOrders();


    @GET
    @Path("/{id}")
	@Operation(summary = "Dados do pedido pelo id", description = "Retorna os dados de um pedido pelo identificador")
	@APIResponse(
		responseCode = "200", description = "SUCCESS", content = {
            @Content(
                mediaType = APPLICATION_JSON,
                schema = @Schema(implementation = OrderResponse.class),
                examples = @ExampleObject(value = "true")
            )}
	)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    Response getOrderById(@PathParam("id") String id);

    @GET
	@Path("/name/{query}")
	@Operation(summary = "Dados do pedido pelo nome", description = "Retorna os dados de um pedido pelo nome")
	@APIResponse(
		responseCode = "200", description = "SUCCESS", content = {
            @Content(
                mediaType = APPLICATION_JSON,
                schema = @Schema(implementation = OrderResponse.class),
                examples = @ExampleObject(value = "true")
            )}
	)
    @APIResponse(
		responseCode = "404", description = "NOT FOUND"
	)
    @APIResponse(
		responseCode = "400", description = "BAD REQUEST"
	)
    Response getOrderByName(
        @PathParam("query") @Valid String query);

    @GET
    @Path("/count")
	@Operation(summary = "Quantidade de pedidos", description = "Retorna a quantidade de pedidos de compra")
	@APIResponse(
		responseCode = "200", description = "SUCCESS", content = {
            @Content(
                mediaType = APPLICATION_JSON,
                schema = @Schema(implementation = Long.class),
                examples = @ExampleObject(value = "true")
            )}
	)
    @APIResponse(responseCode = "404", description = "NOT FOUND")
    @APIResponse(responseCode = "400", description = "BAD REQUEST")
    Response countOrders();

}
