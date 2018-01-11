package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Categories;
import io.dropwizard.models.Project;
import io.dropwizard.services.ProjectService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {
    public ProjectService service;

    public  ProjectResource(ProjectService service){
        this.service = service;
    }

    @GET
    @Path("/allByName")
    @JsonView(View.Public.class)
    public List<Project> getProjects(@QueryParam("name") String customerName) {
        return service.getProjects(customerName);
    }

    @POST
    @Path("/add")
    @JsonView(View.Public.class)
    public void addproject(Categories categorie) {
        service.addProject(categorie.getKlant_ID(), categorie.getProjectnaam(), categorie.getOnderwerpnaam());
    }
}
