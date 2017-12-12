package io.dropwizard.services;

import io.dropwizard.models.Uren;
import io.dropwizard.persistence.DAO.UrenDAO;

import java.util.List;

public class UrenService {
    private UrenDAO dao;
    public UrenService(){
        this.dao = new UrenDAO();
    }

    public List<Uren> getUren(String begindatum, String einddatum, String klant, String project, String onderwerp) {
        return dao.getUrenByKlantProjectOnderwerp(begindatum, einddatum, klant, project, onderwerp);
    }
}
