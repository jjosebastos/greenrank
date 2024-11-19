package br.com.greenrank.controller;

import br.com.greenrank.dto.GreenRankDto;
import br.com.greenrank.exceptions.GreenRankNotFoundException;
import br.com.greenrank.exceptions.GreenRankNotSavedException;
import br.com.greenrank.model.greenRank.GreenRank;
import br.com.greenrank.service.greenRank.GreenRankService;
import br.com.greenrank.service.greenRank.GreenRankServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/green-rank")
public class GreenRankController {

    private final GreenRankService service = GreenRankServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response create(GreenRankDto input){
        if(input.getId() == null){
            try {
                GreenRank greenRank = this.service.create(new GreenRank(null, input.getDateConnection(), input.getDateDisconection(), input.getIdUser(), input.getIdEcoPoint()));
                return Response.status(Response.Status.CREATED).entity(greenRank).build();
            } catch (SQLException | GreenRankNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message","Unexpected error creating Green Rank: " + e.getMessage())
                        ).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Request availabe only for new Green Rank")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, GreenRankDto input){
        try {
            GreenRank updated = this.service.update(new GreenRank(id, input.getDateConnection(),
                    input.getDateDisconection(), input.getIdUser(), input.getIdEcoPoint()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error updating Green Rank: " + s.getMessage()))
                    .build();
        } catch (GreenRankNotFoundException g){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message","Green Rank not found")).build();
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
    public Response delete(@PathParam("id") Long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error deleting Green Rank: " + s.getMessage()))
                    .build();
        } catch (GreenRankNotFoundException g){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message","Green Rank not found")).build();
        }
    }
}
