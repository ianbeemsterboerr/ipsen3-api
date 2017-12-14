package io.dropwizard.services;

import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;

import java.util.List;

public class PersoneelService {
    PersoneelDAO dao = new PersoneelDAO();

    public PersoneelService(){

    }

    public void setWerkzaam(int werkzaam, int id){
        dao.setWerkzaam(werkzaam, id);
    }

    public Personeel getPersoon(String email){
        return dao.getByEmailaddress(email);
    }

    public List<Personeel> getAll() {
        return dao.getAll();
    }
}
