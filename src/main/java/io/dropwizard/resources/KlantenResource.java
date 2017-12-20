package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.auth.Auth;
import io.dropwizard.models.Klant;
import io.dropwizard.models.Personeel;
import io.dropwizard.services.KlantenService;
import io.dropwizard.services.UrenService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Singleton
@Path("/Klanten")
@Produces(MediaType.APPLICATION_JSON)
public class KlantenResource {
    public KlantenService service = new KlantenService();

    @GET
    @Path("/getAll")
    @JsonView(View.Public.class)
    public List<Klant> getCustomer() {
        return service.getCustomer();
    }


}
