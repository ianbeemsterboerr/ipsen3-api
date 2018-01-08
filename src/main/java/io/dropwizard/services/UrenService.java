package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.models.Subject;
import io.dropwizard.models.Uren;
import io.dropwizard.persistence.DAO.UrenDAO;

import java.util.List;

public class UrenService {
    private UrenDAO dao;
    private CustomerService customerService;
    private ProjectService projectService;
    private SubjectService subjectService;

    public UrenService(){

        this.dao = new UrenDAO();
        this.customerService = new CustomerService();
        this.projectService = new ProjectService();
        this.subjectService = new SubjectService();

    }

    //Admin
    public List<Uren> getUrenAdmin(String begindatum, String einddatum, String klant, String project, String onderwerp) {
        return dao.getUrenByKlantProjectOnderwerp(null, begindatum, einddatum, klant, project, onderwerp);
    }
    //Personeel
    public List<Uren> getUren(int id, String begindatum, String einddatum, String klant, String project, String onderwerp) {
        return dao.getUrenByKlantProjectOnderwerp(id, begindatum, einddatum, klant, project, onderwerp);
    }

    public List<Uren> getUrenByPersoneelId(int id) {
        return dao.getByPersoonId(id);
    }

    public void setHours(Uren hour) {

        Customer customer;
        Project project;
        Subject subject;
            if (getCustomer(hour.getCustomerName()) != null) {
                customer = getCustomer(hour.getCustomerName());

                if (getProject(customer.getCustomerId(), hour.getProjectName()) != null) {
                    project = getProject(customer.getCustomerId(), hour.getProjectName());

                    if (getSubject(project.getProjectID(), hour.getSubjectName()) != null) {
                        subject = getSubject(project.getProjectID(), hour.getSubjectName());

                        hour.setCustomerId(customer.getCustomerId());
                        hour.setProjectId(project.getProjectID());
                        hour.setSubjectId(subject.getSubjectId());

                        dao.setHour(hour);

                    } else {
                        System.out.println("subject bestaat al");
                    }
                } else {
                    System.out.println("project bestaat al");
                }
            } else {
                System.out.println("klant bestaat nog niet");
            }

    }

    public List<Uren> getAllUren() {
        return dao.getAllUren();

    }

    private Customer getCustomer(String customerName) {
        Customer customer= customerService.getCustomerByName(customerName);
        return customer;
    }

    private Project getProject(int customerID, String projectName) {
        Project project= projectService.getProjectByCIdAndPName(projectName, customerID);
        return project;
    }

    private Subject getSubject(int projectID, String subjectName) {
        Subject subject = subjectService.getSubjectByPIDSName(projectID, subjectName);
        return subject;
    }

}
