package io.dropwizard.models;

public class Project {

    private int projectId;
    private String projectName;
    private int customerId;

    Project(int projectId, String projectName, int customerId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.customerId = customerId;
    }
}
