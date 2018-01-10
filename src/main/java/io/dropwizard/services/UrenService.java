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
        Customer customer = customerService.getCustomerByName(hour.getCustomerName());
        Project project = projectService.getProjectByCIdAndPName(hour.getProjectName(), customer.getCustomerId());
        Subject subject = subjectService.getSubjectByPIDSName(project.getProjectID(), hour.getSubjectName());

        hour.setCustomerId(customer.getCustomerId());
        hour.setProjectId(project.getProjectID());
        hour.setSubjectId(subject.getSubjectId());


        dao.setHour(hour);


    }
    public void setConfirmed(Uren uur){
        boolean confirmed = uur.isConfirmed() == true ? false : true;
        this.dao.setConfirmed(uur, confirmed);
    }


    public List<Uren> getAllUren() {
        return dao.getAllUren();

    }
    public void updateHour(Uren hour){
        Customer customer = customerService.getCustomerByName(hour.getCustomerName());
        Project project = projectService.getProjectByCIdAndPName(hour.getProjectName(), customer.getCustomerId());
        Subject subject = subjectService.getSubjectByPIDSName(project.getProjectID(), hour.getSubjectName());

        hour.setCustomerId(customer.getCustomerId());
        hour.setProjectId(project.getProjectID());
        hour.setSubjectId(subject.getSubjectId());

        dao.updateHour(hour);
    }
}
