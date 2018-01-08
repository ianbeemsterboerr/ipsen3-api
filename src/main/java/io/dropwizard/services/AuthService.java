package io.dropwizard.services;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;

import javax.inject.Inject;


public class AuthService implements Authenticator<BasicCredentials, Personeel> {
    PersoneelDAO dao;

    @Inject
    public AuthService(PersoneelDAO dao){
        this.dao = dao;

    }
    @Override
    public Optional<Personeel> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Personeel persoon = dao.getByEmailaddress(basicCredentials.getUsername());
        if (persoon != null && persoon.getPassword() != null){
            if(persoon.getPassword().equals(basicCredentials.getPassword())) {
                System.out.println("DEBUGGG");
                return Optional.of(persoon);
            }
        }
        return Optional.absent();
    }
}
