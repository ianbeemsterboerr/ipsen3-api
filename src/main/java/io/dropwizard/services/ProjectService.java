package io.dropwizard.services;


import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.ProjectDAO;

import javax.inject.Inject;

public class ProjectService {

    ProjectDAO dao;
    @Inject
    ProjectService(ProjectDAO dao) {
        this.dao = dao;
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }
}
