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

    @GET
    @Path("/getby")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public List<Uren> getUren(
            @QueryParam("begindatum") String begindatum,
            @QueryParam("einddatum") String einddatum,
            @DefaultValue("")@QueryParam("klant") String klant,
            @DefaultValue("")@QueryParam("project") String project,
            @DefaultValue("")@QueryParam("onderwerp") String onderwerp){

        return service.getUren(begindatum, einddatum, klant, project, onderwerp);
    }
}
