package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Personeel;
import io.dropwizard.services.PersoneelService;


import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/personeel")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "PERSONEEL"})
public class PersoneelResource {
    PersoneelService service;

    @Inject
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
    public void createAccount(Personeel personeel) {
        //TODO @Valid toevoegen, maar werkt niet... ?
        System.out.println(personeel.getVoornaam());
        service.addUser(personeel);
    }

    @POST
    @Path("/wachtwoord")
    @Consumes(MediaType.APPLICATION_JSON)
    public void veranderWachtwoord(@QueryParam("wachtwoord") String newPassword, @QueryParam("id") int ID ) {
        System.out.println(newPassword + " " + ID);
        service.changePassword(newPassword, ID);
    }
}
