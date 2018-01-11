package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.ProjectDAO;

import java.util.List;

public class ProjectService {
    CustomerService customerService;
    ProjectDAO dao;
    public ProjectService(ProjectDAO dao, CustomerService customerService) {
        this.dao = dao;
        this.customerService = customerService;
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }

    public List<Project> getProjects(String customerName) {


        Customer customer = customerService.getCustomerByName(customerName);


        return this.dao.getProjectByID(customer.getCustomerId());
    }
}
