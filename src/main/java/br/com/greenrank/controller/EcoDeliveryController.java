package br.com.greenrank.controller;

import br.com.greenrank.dto.EcoDeliveryDto;
import br.com.greenrank.exceptions.EcoDeliveryNotFoundException;
import br.com.greenrank.exceptions.EcoDeliveryNotSavedException;
import br.com.greenrank.model.ecoDelivery.EcoDelivery;
import br.com.greenrank.service.ecoDelivery.EcoDeliveryService;
import br.com.greenrank.service.ecoDelivery.EcoDeliveryServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/eco-delivery")
public class  EcoDeliveryController {
    private final EcoDeliveryService service = EcoDeliveryServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(EcoDeliveryDto input) {
        if (input.getId() == null) {
            try {
                EcoDelivery ecoDelivery = service.create(new EcoDelivery(null, input.getTypeMaterial(), input.getDateReceipt(),
                        input.getQuantity(), input.getIdCustomer(), input.getIdEcoPoint(), input.getIdEnterprise()));
                return Response.status(Response.Status.CREATED).entity(ecoDelivery).build();
            } catch (SQLException | EcoDeliveryNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message", "Unexpected error creating Eco Delivery" + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Method availabe only for new Eco Deliveries"))
                    .build();

        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EcoDeliveryDto input) {
        try {
            EcoDelivery updated = this.service.update(new EcoDelivery(id,input.getTypeMaterial(), input.getDateReceipt(),
                    input.getQuantity(), input.getIdCustomer(), input.getIdEcoPoint(), input.getIdEnterprise()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating Eco Delivery"))
                    .build();
        } catch (EcoDeliveryNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll() {
        return Response.status(Response.Status.OK)
                .entity(this.service.getAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try{
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error deleting Eco Delivery"))
                    .build();
        } catch (EcoDeliveryNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
