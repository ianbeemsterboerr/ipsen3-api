package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.org.apache.xpath.internal.SourceTree;
import io.dropwizard.View;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.models.Personeel;
import io.dropwizard.services.PersoneelService;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/personeel")
@Produces(MediaType.APPLICATION_JSON)
public class PersoneelResource {
    PersoneelService service = new PersoneelService();

    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public String getPersoon(){
        return "LOL xD";
    }

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

    @POST
    @Path("/werkzaam")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWerkzaam(@QueryParam("id") int id, @QueryParam("werkzaam") String werkzaam){
        int werkzaamBool = werkzaam.equals("1") ? 0 : 1;
        service.setWerkzaam(werkzaamBool, id);
    }

    @POST
    @Path("add")
    @Consumes (MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    public void createAccount(Personeel personeel) {
        //TODO @Valid toevoegen, maar werkt niet... ?
        System.out.println(personeel.getVoornaam());
        service.addUser(personeel);
    }

    @POST
    @Path("wachtwoord")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void veranderWachtwoord(@QueryParam("wachtwoord") String newPassword, @QueryParam("id") int ID ) {
        System.out.println(newPassword + " " + ID);
        service.changePassword(newPassword, ID);
    }
}
