package br.com.greenrank.controller;

import br.com.greenrank.dto.TelephoneDto;
import br.com.greenrank.exceptions.TelephoneNotFoundException;
import br.com.greenrank.exceptions.TelephoneNotSavedException;
import br.com.greenrank.model.telephone.Telephone;
import br.com.greenrank.service.telephone.TelephoneService;
import br.com.greenrank.service.telephone.TelephoneServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/telephone")
public class TelephoneController {

    private final TelephoneService service = TelephoneServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(@BeanParam TelephoneDto input){
        if(input.getId() == null){
            try {
                Telephone telephone = this.service.create(new Telephone(null, input.getDdd(), input.getNumber(),
                        input.getType(), input.getIdEcoPoint(), input.getIdUser()));
                return Response.status(Response.Status.CREATED).entity(telephone).build();
            } catch (SQLException | TelephoneNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message", "Unexpected error creating telephone: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Telephone already exists"))
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@BeanParam TelephoneDto input, @PathParam("id") Long id){
        try {
            Telephone updated = this.service.update(new Telephone(id, input.getDdd(), input.getNumber(),
                    input.getType(), input.getIdEcoPoint(), input.getIdUser()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating telephone "))
                    .build();
        } catch (TelephoneNotFoundException t){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "Telephone not found"))
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll(){
        return Response.status(Response.Status.OK).entity(this.service.getAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error deleting telephone"))
                    .build();
        } catch (TelephoneNotFoundException t){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "Telephone Not found"))
                    .build();
        }
    }
}
