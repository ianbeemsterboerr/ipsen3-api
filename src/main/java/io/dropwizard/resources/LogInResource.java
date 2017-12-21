package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.auth.Auth;
import io.dropwizard.models.Personeel;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class LogInResource {

    @GET
    @Path("/login")
    @JsonView(View.Public.class)
    public Personeel logIn(@Auth Personeel personeel){
        return personeel;
    }
}
