package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Uren;
import io.dropwizard.services.UrenService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/uren")
@Produces(MediaType.APPLICATION_JSON)
public class UrenResource {
    public UrenService service = new UrenService();

    //  Voorbeelden voor de URL:
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1&klant=WebEdu
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1&klant=WebEdu&project=UrenRegistratieApplicatie
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1&klant=WebEdu&project=UrenRegistratieApplicatie&onderwerp=Applicatie


    /**
     * Ontvangt een personeelID en geeft alle gewerkte uren voor deze personeelID terug.
     * @param id
     * @return
     */
    @GET
    @Path("/getbyid")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public List<Uren> getUren(
            @QueryParam("id") int id){

        System.out.println(id);
        return service.getUrenByPersoneelId(id);
    }

    @GET
    @Path("/getall")
    @JsonView(View.OnlyAdmins.class)
    @RolesAllowed("ADMIN")
    public List<Uren> getAllUren(){
        return service.getAllUren();
    }

    @POST
    @Path("/setHour")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    public void setHours( Uren uren) {
        System.out.println(uren.getEmployeeId());
        this.service.setHours(uren);
    }

    @POST
    @Path("/confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    public void setConfirmed(Uren uur){
        this.service.setConfirmed(uur);
    }

    @POST
    @Path("/updateHour")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void updateHour(Uren hour){ this.service.updateHour(hour);}

}
