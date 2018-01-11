package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.org.apache.xpath.internal.SourceTree;
import io.dropwizard.View;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.models.Personeel;
import io.dropwizard.services.PersoneelService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/personeel")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "PERSONEEL"})
public class PersoneelResource {
    private  PersoneelService service;

    public PersoneelResource(PersoneelService service){
        this.service = service;
    }

    @GET
    @Path("/getall")
    @JsonView(View.Public.class)
    public List<Personeel> getAll(){
        return service.getAll();
    }

    @POST
    @Path("/werkzaam")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWerkzaam(@QueryParam("id") int id, @QueryParam("werkzaam") String werkzaam){
        service.setWerkzaam(werkzaam, id);
    }

    @POST
    @Path("/add")
    @Consumes (MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    public void createAccount(@Valid Personeel personeel) {
        //TODO @Valid toevoegen, maar werkt niet... ?
        service.addUser(personeel);
    }

    @POST
    @Path("/wachtwoord")
    @Consumes(MediaType.APPLICATION_JSON)
    public void veranderWachtwoord(@QueryParam("wachtwoord") String newPassword, @QueryParam("id") int ID ) {
        service.changePassword(newPassword, ID);
    }
}
