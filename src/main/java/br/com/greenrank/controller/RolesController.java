package br.com.greenrank.controller;

import br.com.greenrank.dto.RolesDto;
import br.com.greenrank.exceptions.RoleNotSavedException;
import br.com.greenrank.model.roles.Roles;
import br.com.greenrank.service.roles.RoleService;
import br.com.greenrank.service.roles.RoleServiceFactory;

import javax.management.relation.RoleNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/roles")
public class RolesController {
    private final RoleService service = RoleServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(RolesDto input){
        if(input.getId() == null){
            try{
                Roles roles = this.service.create(new Roles(null,input.getName(), input.getDescription()));
                return Response.status(Response.Status.CREATED).entity(roles).build();
            } catch (SQLException | RoleNotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message","Unexpected error creating Role: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message","Method availabe only for new roles")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RolesDto input){
        try{
            Roles updated = this.service.update(new Roles(id,input.getName(), input.getDescription()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error updating Roles: " + s.getMessage()))
                    .build();
        } catch (RoleNotFoundException r){
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll(){
        return Response.status(Response.Status.OK).entity(service.getAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        try {
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error deleting role: " + s.getMessage()))
            .build();
        } catch (RoleNotFoundException r){
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

}
