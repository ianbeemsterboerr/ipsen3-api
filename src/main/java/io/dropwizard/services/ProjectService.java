package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.ProjectDAO;

import java.util.List;

public class ProjectService {

    ProjectDAO dao;
    public ProjectService(ProjectDAO dao) {
        this.dao = dao;
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }

    public List<Project> getProjects(String customerName) {
        CustomerService customerService = new CustomerService();

        Customer customer = customerService.getCustomerByName(customerName);


        return this.dao.getProjectByID(customer.getCustomerId());
    }
}
