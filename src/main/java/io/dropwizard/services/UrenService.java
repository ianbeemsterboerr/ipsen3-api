package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.models.Subject;
import io.dropwizard.models.Uren;
import io.dropwizard.persistence.DAO.CustomerDAO;
import io.dropwizard.persistence.DAO.ProjectDAO;
import io.dropwizard.persistence.DAO.SubjectDAO;
import io.dropwizard.persistence.DAO.UrenDAO;

import java.util.List;

public class UrenService {
    private UrenDAO dao;
    private CustomerDAO customerDao;
    private ProjectDAO projectDao;
    private SubjectDAO subjectDao;

    public UrenService(UrenDAO urenDao, CustomerDAO customerDao, ProjectDAO ProjectDao, SubjectDAO subjectDao){
        this.dao = urenDao;
        this.customerDao = customerDao;
        this.projectDao = ProjectDao;
        this.subjectDao = subjectDao;
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
    public void setConfirmed(Uren uur){
        boolean confirmed = uur.isConfirmed() == true ? false : true;
        this.dao.setConfirmed(uur, confirmed);
    }


    public List<Uren> getAllUren() {
        return dao.getAllUren();

    }
    public void updateHour(Uren hour){
        Customer customer = customerDao.getCustomerByName(hour.getCustomerName());
        Project project = projectDao.getProjectByCIdAndPName(hour.getProjectName(), customer.getCustomerId());
        Subject subject = subjectDao.getSubjectByPIDSName(project.getProjectID(), hour.getSubjectName());

        hour.setCustomerId(customer.getCustomerId());
        hour.setProjectId(project.getProjectID());
        hour.setSubjectId(subject.getSubjectId());

        dao.updateHour(hour);
    }

    private Customer getCustomer(String customerName) {
        Customer customer= customerDao.getCustomerByName(customerName);
        return customer;
    }

    private Project getProject(int customerID, String projectName) {
        Project project= projectDao.getProjectByCIdAndPName(projectName, customerID);
        return project;
    }

    private Subject getSubject(int projectID, String subjectName) {
        Subject subject = subjectDao.getSubjectByPIDSName(projectID, subjectName);
        return subject;
    }


}
