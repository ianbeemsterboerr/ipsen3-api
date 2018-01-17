package io.dropwizard.services;

import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class PersoneelService {
    PersoneelDAO dao;
    private final String STANDAARDWACHTWOORD = "Welkom1";

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
        personeel.setWachtwoord(BCrypt.hashpw(STANDAARDWACHTWOORD, BCrypt.gensalt()));
        dao.add(personeel);
    }

    public void changePassword(String newPassword, int ID, String oldPassword) {

        String passwordCheck = dao.getPasswordById(ID);

        if(BCrypt.checkpw(oldPassword, passwordCheck)){
        String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        dao.setWachtwoord(hash, ID);
            System.out.println("Hetzelfde:");
        }
    }
}
