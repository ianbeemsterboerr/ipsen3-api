package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Customer;
import io.dropwizard.services.CustomerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Singleton
@Path("/Klanten")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    public CustomerService service;

    @Inject
    public CustomerResource(CustomerService service){
        this.service = service;
    }

    @GET
    @Path("/getAll")
    @JsonView(View.Public.class)
    public List<Customer> getCustomer() {
        return service.getCustomer();
    }


}
