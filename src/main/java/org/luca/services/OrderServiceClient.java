package org.luca.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.luca.models.OrderDTO;

import java.util.List;

@RegisterRestClient(configKey = "order-service")
public interface OrderServiceClient {
//    @GET
//    @Path("/orders/user/{userId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    List<OrderDTO> getOrdersByUserId(@PathParam("userId") Long userId);



    @GET
    @Path("/orders/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    List<OrderDTO> getOrdersByUserId(@PathParam("userId") Long userId);
}
