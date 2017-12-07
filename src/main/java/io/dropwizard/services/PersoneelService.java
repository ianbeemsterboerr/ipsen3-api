package io.dropwizard.services;

import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.PersoneelDAO;

public class PersoneelService {
    PersoneelDAO dao = new PersoneelDAO();

    public PersoneelService(){

    }
    public Personeel getPersoon(String email){
        return dao.getByEmailaddress(email);
    }
}
