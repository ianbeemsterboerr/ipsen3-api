package io.dropwizard.services;

import io.dropwizard.models.Uren;
import io.dropwizard.persistence.DAO.UrenDAO;

import java.util.List;

public class UrenService {
    private UrenDAO dao;
    public UrenService(){
        this.dao = new UrenDAO();
    }

    //Admin
    public List<Uren> getUrenAdmin(String begindatum, String einddatum, String klant, String project, String onderwerp) {
        return dao.getUrenByKlantProjectOnderwerp(null, begindatum, einddatum, klant, project, onderwerp);
    }
    //Personeel
    public List<Uren> getUren(int id, String begindatum, String einddatum, String klant, String project, String onderwerp) {
        return dao.getUrenByKlantProjectOnderwerp(id, begindatum, einddatum, klant, project, onderwerp);
    }
}
