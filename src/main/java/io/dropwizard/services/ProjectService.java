package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.CustomerDAO;
import io.dropwizard.persistence.DAO.ProjectDAO;

import java.util.List;

public class ProjectService {
    CustomerDAO customerDao;
    ProjectDAO dao;
    public ProjectService(ProjectDAO projectDao, CustomerDAO customerDao) {
        this.dao = projectDao;
        this.customerDao = customerDao;
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }

    public List<Project> getProjects(String customerName) {


        Customer customer = customerDao.getCustomerByName(customerName);


        return this.dao.getProjectByID(customer.getCustomerId());
    }
}
