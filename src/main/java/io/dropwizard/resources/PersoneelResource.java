package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Personeel;
import io.dropwizard.services.PersoneelService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/personeel")
@Produces(MediaType.TEXT_PLAIN)
public class PersoneelResource {
    PersoneelService service = new PersoneelService();

    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public String getPersoon(){
        return "LOL xD";
    }

    @POST
    @Path("/{name}{password}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean logIn(@PathParam("name") String name, @PathParam("password") String password){
        return service.login(name, password);
    }
}
