package br.com.greenrank.controller;

import br.com.greenrank.dto.CustomerRequestDto;
import br.com.greenrank.exceptions.CustomerNotFoundException;
import br.com.greenrank.exceptions.CustomerNotSavedException;
import br.com.greenrank.exceptions.UserNotFoundException;
import br.com.greenrank.model.user.Customer;
import br.com.greenrank.model.user.User;
import br.com.greenrank.service.customer.CustomerService;
import br.com.greenrank.service.customer.CustomerServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/customer")
public class CustomerController {
    private final CustomerService service = CustomerServiceFactory.create();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(CustomerRequestDto requestDto) {
        if (requestDto.getIdUser() == null || requestDto.getIdCustomer() == null) {
            try {
                User userObject = new User(null, requestDto.getUsername(), requestDto.getPassword(), requestDto.getIdEmail());
                Customer customerObject = new Customer(null,
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getIdEmail(),
                        null, requestDto.getName(),
                        requestDto.getCpf(), requestDto.getRg(),
                        requestDto.getBirthDate(), requestDto.getGender(),
                        requestDto.getIdUser());

                Customer customer = this.service.create(userObject, customerObject);
                return Response.status(Response.Status.CREATED).entity(customer).build();
            } catch (SQLException | CustomerNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "Method available only for new Customers")).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CustomerRequestDto requestDto) {
        try {
            User userObject = new User(id, requestDto.getUsername(), requestDto.getPassword(), requestDto.getIdEmail());
            Customer customerObject = new Customer(id, requestDto.getUsername(), requestDto.getPassword(),
                    requestDto.getIdEmail(), requestDto.getIdCustomer(),
                    requestDto.getName(), requestDto.getCpf(),
                    requestDto.getRg(), requestDto.getBirthDate(),
                    requestDto.getGender(), id);

            Customer updated = this.service.update(userObject, customerObject);
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Message", "Unexpected error updating Customer")).build();
        } catch (CustomerNotFoundException | UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Message", "Customer not found")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll() {
        return Response.status(Response.Status.OK).entity(service.getAll()).build();
    }
}
