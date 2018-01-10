package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Customer;
import io.dropwizard.services.CustomerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Singleton
@Path("/Klanten")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    public CustomerService service = new CustomerService();

    @GET
    @Path("/getAll")
    @JsonView(View.Public.class)
    public List<Customer> getCustomer() {
        return service.getCustomer();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    public void createAccount(Customer customer) {
        //TODO @Valid toevoegen, maar werkt niet... ?
        System.out.println(customer.getCustomerName());
        service.addCustomer(customer);
    }



}
