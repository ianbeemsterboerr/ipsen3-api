package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Categories;
import io.dropwizard.models.Subject;
import io.dropwizard.services.SubjectService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/subjects")
@Produces(MediaType.APPLICATION_JSON)
public class SubjectResource {
    public SubjectService service = new SubjectService();

    @GET
    @Path("/allByName")
    @JsonView(View.Public.class)
    public List<Subject> getSubjects(@QueryParam("project") String projectName, @QueryParam("klant") String customerName ) {
        return service.getSubjects(projectName, customerName);
    }

    @POST
    @Path("/add")
    @JsonView(View.Public.class)
    public void addSubject(Categories category){
        service.addSubject(category.getProject_ID(), category.getOnderwerpnaam());
    }
}
