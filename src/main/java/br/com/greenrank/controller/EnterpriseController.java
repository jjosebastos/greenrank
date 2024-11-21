package br.com.greenrank.controller;

import br.com.greenrank.dto.EnterpriseDto;
import br.com.greenrank.dto.EnterpriseRequestDto;
import br.com.greenrank.dto.UserDto;
import br.com.greenrank.exceptions.EnterpriseNotFoundException;
import br.com.greenrank.exceptions.EnterpriseNotSavedException;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.model.user.Enterprise;
import br.com.greenrank.model.user.User;
import br.com.greenrank.service.enterprise.EnterpriseService;
import br.com.greenrank.service.enterprise.EnterpriseServiceFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/enterprise")
public class EnterpriseController {
    private final EnterpriseService service = EnterpriseServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(EnterpriseRequestDto requestDto) {
        if(requestDto.getIdDad() == null || requestDto.getIdUser() == null) {
            try {
                User userObject = new User(null, requestDto.getUsername(), requestDto.getPassword(), requestDto.getIdEmail());
                Enterprise enterpriseObject = new Enterprise(null, requestDto.getUsername(), requestDto.getPassword(),
                        requestDto.getIdEmail(), null, requestDto.getLegalName(),
                        requestDto.getTradeName(), requestDto.getCnpj(),
                        requestDto.getCompanyType(), null);

                Enterprise enterprise = this.service.create(userObject, enterpriseObject);
                return Response.status(Response.Status.CREATED).entity(enterprise).build();
            } catch (SQLException | EnterpriseNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Method available only for new Enterprises")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EnterpriseRequestDto requestDto) {
        try {
            User userObject = new User(id, requestDto.getUsername(), requestDto.getPassword(), requestDto.getIdEmail());
            Enterprise enterpriseObject = new Enterprise(id, requestDto.getUsername(), requestDto.getPassword(),
                    requestDto.getIdEmail(), requestDto.getIdEnterprise(),
                    requestDto.getLegalName(), requestDto.getTradeName(),
                    requestDto.getCnpj(), requestDto.getCompanyType(), id);

            Enterprise updated = this.service.update(userObject, enterpriseObject);
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating Enterprise")).build();
        } catch (EnterpriseNotFoundException | UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "Enterprise not found")).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll(){
        return Response.status(Response.Status.OK).entity(service.getAll()).build();
    }


}
