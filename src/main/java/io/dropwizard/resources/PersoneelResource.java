package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.auth.Auth;
import io.dropwizard.models.Personeel;
import io.dropwizard.services.PersoneelService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/personeel")
@Produces(MediaType.APPLICATION_JSON)
public class PersoneelResource {
    PersoneelService service = new PersoneelService();

    @GET
    @Path("/login")
    @JsonView(View.Public.class)
    public Personeel logIn(@Auth Personeel personeel){
        System.out.println(personeel.getPassword());
        return personeel;
    }

    @GET
    @Path("/getall")
    @JsonView(View.Public.class)
    public List<Personeel> getAll(){
        return service.getAll();
    }


}
