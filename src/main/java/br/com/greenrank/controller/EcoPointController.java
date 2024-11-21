package br.com.greenrank.controller;

import br.com.greenrank.dto.EcoPointDto;
import br.com.greenrank.exceptions.EcoPointNotFoundException;
import br.com.greenrank.exceptions.EcoPointNotSavedException;
import br.com.greenrank.model.ecoPoint.EcoPoint;
import br.com.greenrank.service.ecoPoint.EcoPointService;
import br.com.greenrank.service.ecoPoint.EcoPointServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/eco-point")
public class EcoPointController {
    private final EcoPointService service = EcoPointServiceFactory.create();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(EcoPointDto input){
        if(input.getId() == null){
            try {
                EcoPoint ecoPoint = this.service.create(new EcoPoint(null, input.getName(), input.getCnpj(), input.getHourOpen(), input.getHourClose()));
                return Response.status(Response.Status.CREATED).entity(ecoPoint).build();
            } catch (SQLException | EcoPointNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message", "Unexpected error creating Eco Point: " + e.getMessage())).build();
            }
        }  else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "EcoPoint already exists")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EcoPointDto input){
        try {
            EcoPoint updated = this.service.update(new EcoPoint(id, input.getName(), input.getCnpj(), input.getHourOpen(), input.getHourClose()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating Eco Point: " + s.getMessage())).build();

        } catch (EcoPointNotFoundException s){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "EcoPoint not found")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll(){
        return Response.status(Response.Status.OK)
                .entity(this.service.listAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error deleting Eco Point: " + e.getMessage())).build();
        } catch (EcoPointNotFoundException s){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "EcoPoint not found")).build();
        }
    }
}
