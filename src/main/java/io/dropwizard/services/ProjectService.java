package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.ProjectDAO;

import java.util.List;

public class ProjectService {

    private Project project = null;
    private CustomerService customerService;
    private SubjectService subjectService;
    private ProjectDAO dao;

    public ProjectService(ProjectDAO dao, CustomerService customerService, SubjectService subjectService) {
        this.dao = dao;
        this.customerService = customerService;
        this.subjectService = subjectService;
    }

    public Project getProjectByCIdAndPName(String projectName, int customerId) {
        return this.dao.getProjectByCIdAndPName(projectName, customerId);
    }

    public List<Project> getProjects(String customerName) {


        Customer customer = customerService.getCustomerByName(customerName);


        return this.dao.getProjectByID(customer.getCustomerId());
    }

    public void addProject(int klant_ID, String projectnaam, String onderwerpnaam) {
        dao.addProject(projectnaam, klant_ID);

        project = dao.getProjectByCIdAndPName(projectnaam, klant_ID);
        subjectService.addSubject(project.getProjectID(), onderwerpnaam);
    }
}
