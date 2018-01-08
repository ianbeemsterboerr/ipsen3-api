package io.dropwizard.services;

import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.ProjectDAO;

public class ProjectService {

    ProjectDAO dao = new ProjectDAO();

    ProjectService() {

    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }
}
