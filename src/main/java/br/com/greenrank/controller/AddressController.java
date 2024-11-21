package br.com.greenrank.controller;

import br.com.greenrank.dto.AddressDto;
import br.com.greenrank.exceptions.AddressNotFoundException;
import br.com.greenrank.exceptions.AddressNotSavedException;
import br.com.greenrank.model.address.Address;
import br.com.greenrank.service.address.AddressService;
import br.com.greenrank.service.address.AddressServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/address")
public class AddressController {

    private final AddressService service = AddressServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(AddressDto input){
        if(input.getId() == null){
            try {
                Address address = this.service.create(new Address(null, input.getStreet(), input.getNumber(), input.getNeighborhood(), input.getCity(),
                        input.getState(), input.getComplement(), input.getCep(), input.getIdUser(), input.getIdEcoPoint()));
                return Response.status(Response.Status.CREATED).entity(address).build();
            } catch (SQLException | AddressNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("Message",
                        "Unexpected error on address insertion: " + e.getMessage())).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of(
                    "Message",
                    "Method availabe only for new addresses")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, AddressDto input){
        try {
            Address updated = this.service.update(new Address(id, input.getStreet(), input.getNumber(), input.getNeighborhood(), input.getCity(),
                    input.getState(), input.getComplement(), input.getCep(), input.getIdUser(), input.getIdEcoPoint()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (AddressNotFoundException s) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error on address insertion: " + e.getMessage()))
                    .build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll(){
        return Response.status(Response.Status.OK)
                .entity(this.service.findAll())
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e ) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error on address insertion: " + e.getMessage())).build();
        } catch (AddressNotFoundException s) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
