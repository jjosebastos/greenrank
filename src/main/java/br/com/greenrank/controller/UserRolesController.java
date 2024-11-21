package br.com.greenrank.controller;

import br.com.greenrank.dao.userRoles.UserRolesDao;
import br.com.greenrank.dao.userRoles.UserRolesDaoFactory;
import br.com.greenrank.dto.UserRolesDto;
import br.com.greenrank.exceptions.UserRolesNotFoundException;
import br.com.greenrank.exceptions.UserRolesNotSavedException;
import br.com.greenrank.model.userRoles.UserRoles;
import br.com.greenrank.service.userRoles.UserRolesService;
import br.com.greenrank.service.userRoles.UserRolesServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/api")
public class UserRolesController {

    private final UserRolesService service = UserRolesServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(UserRolesDto input){
        if(input.getIdUserRoles() == null){
            try {
                UserRoles userRoles = this.service.create(new UserRoles(null, input.getIdUser(), input.getIdRole()));
                return Response.status(Response.Status.CREATED).entity(userRoles).build();
            } catch (SQLException | UserRolesNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("Message", "Internal error while creating user role: " + e.getMessage())).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "This method only availabe for new User Roles")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserRolesDto input){
        try {
            UserRoles updated = this.service.update(new UserRoles(id, input.getIdUser(), input.getIdRole()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Internal error while updating user role: " + s.getMessage())).build();
        } catch (UserRolesNotFoundException u){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "User roles not found: " + u.getMessage())).build();
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
        try{
            this.service.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Internal error while deleting user role: " + s.getMessage())).build();
        } catch (UserRolesNotFoundException u){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "User roles not found: " + u.getMessage())).build();
        }
    }

}
