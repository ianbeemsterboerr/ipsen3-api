package io.dropwizard.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Project {

    @NotEmpty
    @Length(max = 40)
    private String projectNaam;

    private int projectID;


    public int getProjectID(){return projectID; }

    public void setProjectID(int projectID){this.projectID = projectID;}

    public String getProjectNaam(){return projectNaam;}

    public void setProjectNaam(String projectNaam){this.projectNaam = projectNaam;}

    public Project(int projectID, String projectNaam){

        this.projectID = projectID;

        this.projectNaam = projectNaam;

    }
}
