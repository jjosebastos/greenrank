package br.com.greenrank.controller;

import br.com.greenrank.service.rank.RankService;
import br.com.greenrank.service.rank.RankServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest/rank")
public class Rank {

    private final RankService service = RankServiceFactory.create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getRank() {
        return Response.status(Response.Status.OK)
                .entity(this.service.getAll())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/top")
    public Response getTopRank() {
        return Response.status(Response.Status.OK)
                .entity(this.service.getTopRanks())
                .build();
    }
}
