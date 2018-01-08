package io.dropwizard.services;

import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;

import javax.inject.Inject;
import java.util.List;

public class PersoneelService {
    PersoneelDAO dao;

    @Inject
    public PersoneelService(PersoneelDAO dao){
        this.dao = dao;
    }

    public void setWerkzaam(String werkzaam, int id){
        int werkzaamBool = werkzaam.equals("1") ? 0 : 1;
        dao.setWerkzaam(werkzaamBool, id);
    }

    public Personeel getPersoon(String email){
        return dao.getByEmailaddress(email);
    }

    public List<Personeel> getAll() {
        return dao.getAll();
    }

    public void addUser(Personeel personeel){
        dao.add(personeel);
    }

    public void changePassword(String newPassword, int ID) {
        dao.setWachtwoord(newPassword, ID);
    }
}
