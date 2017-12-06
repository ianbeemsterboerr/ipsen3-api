package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/personeel")
@Produces(MediaType.TEXT_PLAIN)
public class PersoneelResource {

    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public String getPersoon(){
        return "LOL xD";
    }
}
