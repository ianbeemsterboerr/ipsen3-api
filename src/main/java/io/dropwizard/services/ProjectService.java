package io.dropwizard.services;

import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.ProjectDAO;

public class ProjectService {

    ProjectDAO dao;
    public ProjectService(ProjectDAO dao) {
        this.dao = dao;
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }
}
