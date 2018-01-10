package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.models.Subject;
import io.dropwizard.persistence.DAO.SubjectDAO;

import java.util.List;

public class SubjectService {

    SubjectDAO dao;
    public SubjectService(SubjectDAO dao) {
        this.dao = dao;
    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        return this.dao.getSubjectByPIDSName(projectID, subjectName);
    }

    public List<Subject> getSubjects(String projectName, String customerName) {
        System.out.println("1: " +projectName);
        System.out.println("2: " + customerName);
        CustomerService customerService = new CustomerService();
        System.out.println("3: "+customerService);
        Customer customer = customerService.getCustomerByName(customerName);
        System.out.println("4: "+customer);
        ProjectService projectService = new ProjectService();
        System.out.println("5: "+projectService);
        Project project = projectService.getProjectByCIdAndPName(projectName, customer.getCustomerId());
        System.out.println("6: "+project);


        return this.dao.getSubjects(project.getProjectID());

    }
}
