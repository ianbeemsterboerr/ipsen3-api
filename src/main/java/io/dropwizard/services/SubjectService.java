package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.models.Subject;
import io.dropwizard.persistence.DAO.SubjectDAO;

import java.util.List;

public class SubjectService {
    ProjectService projectService;
    CustomerService customerService;
    SubjectDAO dao;
    public SubjectService(SubjectDAO dao, ProjectService projectService, CustomerService customerService) {
        this.dao = dao;
        this.projectService = projectService;
        this.customerService = customerService;
    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        return this.dao.getSubjectByPIDSName(projectID, subjectName);
    }

    public List<Subject> getSubjects(String projectName, String customerName) {

        Customer customer = customerService.getCustomerByName(customerName);
        Project project = projectService.getProjectByCIdAndPName(projectName, customer.getCustomerId());
        return this.dao.getSubjects(project.getProjectID());
    }

    public void addSubject(int projectID, String onderwerpnaam) {
        dao.addSubject(onderwerpnaam, projectID);
    }


    }
