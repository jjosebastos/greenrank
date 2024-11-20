package br.com.greenrank.controller;

import br.com.greenrank.dto.UserDto;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.exceptions.UserNotSavedException;
import br.com.greenrank.model.user.User;
import br.com.greenrank.service.user.UserService;
import br.com.greenrank.service.user.UserServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/user")
public class UserController {

    private final UserService userService = UserServiceFactory.create();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDto input) {
        if(input.getId()==null){
            try {
                User user = this.userService.create(new User(null, input.getUsername(), input.getPassword(), input.getEmail()));
                return Response.status(Response.Status.CREATED)
                        .entity(user)
                        .build();
            } catch (UserNotSavedException | SQLException  e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                        entity(Map.of(
                                "mensagem",
                                "erro inesperado ao tentar inserir pessoa"))
                        .build();
            }
        } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(Map.of(
                        "mesagem",
                        "erro inesperado ao tentar inserir pessoa"
                )).build();
        }
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UserDto input) {
        try {
            User updated = this.userService.update(new User(id, input.getUsername(), input.getPassword(), input.getEmail()));
            return Response.status(Response.Status.CREATED)
                    .entity(updated)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating user")).build();
        } catch (UserNotFoundException s) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Method available only for new users")).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Response.Status.OK).entity(this.userService.findAll()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.userService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message","Unexpected error deleting user")).build();
        } catch (UserNotFoundException s) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Method available only for new users")).build();
        }
    }

}
