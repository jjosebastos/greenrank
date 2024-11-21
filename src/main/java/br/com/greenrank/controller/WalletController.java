package br.com.greenrank.controller;

import br.com.greenrank.dto.WalletDto;
import br.com.greenrank.exceptions.WalletNotFoundException;
import br.com.greenrank.exceptions.WalletNotSavedException;
import br.com.greenrank.model.wallet.Wallet;
import br.com.greenrank.service.wallet.WalletService;
import br.com.greenrank.service.wallet.WalletServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/wallet")
public class WalletController {
    private final WalletService service = WalletServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(@BeanParam WalletDto input) {
        if(input.getId() == null){
            try {
                Wallet wallet = this.service.create(new Wallet(null, input.getBalance(), input.getIdUser()));
                return Response.status(Response.Status.CREATED).entity(wallet).build();
            } catch (SQLException | WalletNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message","Unexpected error on wallet: " + e.getMessage())).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message ","This method is only for new wallet")).build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @BeanParam WalletDto input) {
        try{
            Wallet updated = this.service.update(new Wallet(id, input.getBalance(), input.getIdUser()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (WalletNotFoundException s){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message","Wallet not found: " + s.getMessage())).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error on wallet: " + e.getMessage())).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response get() {
        return Response.status(Response.Status.OK)
                .entity(this.service.listAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.service.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WalletNotFoundException w){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message","Wallet not found: " + w.getMessage())).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error on wallet: " + e.getMessage())).build();
        }
    }

}
