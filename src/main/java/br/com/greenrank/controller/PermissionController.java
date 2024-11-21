package br.com.greenrank.controller;

import br.com.greenrank.dto.PermissionDto;
import br.com.greenrank.exceptions.PermissionNotFoundException;
import br.com.greenrank.exceptions.PermissionNotSavedException;
import br.com.greenrank.model.permission.Permission;
import br.com.greenrank.service.permission.PermissionService;
import br.com.greenrank.service.permission.PermissionServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/permissions")
public class PermissionController {

    private final PermissionService service = PermissionServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(PermissionDto input) {
        if(input.getId() == null) {
            try {
                Permission permission = this.service.create(new Permission(null, input.getName(), input.getDescription(),
                        input.getIdUser()));
                return Response.status(Response.Status.CREATED).entity(permission).build();
            } catch (SQLException | PermissionNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message", "Unexpected error creating Permission: " + e.getMessage())).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "This method only allow new Permission"))
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PermissionDto input) {
        try {
            Permission updated = this.service.update(new Permission(id, input.getName(),
                    input.getDescription(), input.getIdUser()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating Permission: " + s.getMessage()))
                    .build();
        } catch (PermissionNotFoundException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Permission not found: " + e.getMessage())).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getALL() {
        return Response.status(Response.Status.OK).entity(service.getAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error deleting Permission: " + s.getMessage())).build();
        } catch (PermissionNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "Permission not found: " + e.getMessage())).build();
        }
    }

}
