package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.models.Subject;
import io.dropwizard.persistence.DAO.SubjectDAO;

import java.util.List;

public class SubjectService {

    SubjectDAO dao = new SubjectDAO();
    public SubjectService() {

    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        return this.dao.getSubjectByPIDSName(projectID, subjectName);
    }

    public List<Subject> getSubjects(String projectName, String customerName) {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomerByName(customerName);
        ProjectService projectService = new ProjectService();
        Project project = projectService.getProjectByCIdAndPName(projectName, customer.getCustomerId());


        return this.dao.getSubjects(project.getProjectID());

    }

    public void addSubject(int projectID, String onderwerpnaam) {
        dao.addSubject(projectID, onderwerpnaam);
    }


    }
