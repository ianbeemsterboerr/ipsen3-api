package io.dropwizard.services;

import io.dropwizard.models.Klant;
import io.dropwizard.persistence.DAO.KlantDAO;

import java.util.List;

public class KlantenService {

    KlantDAO dao = new KlantDAO();

    public KlantenService(){
    }

    public List<Klant> getCustomer() {
        return dao.getCustomer();
    }

}
