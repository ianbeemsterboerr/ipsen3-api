package io.dropwizard.services;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;
import org.mindrot.jbcrypt.BCrypt;


public class AuthService implements Authenticator<BasicCredentials, Personeel>, Authorizer<Personeel> {
    private PersoneelDAO dao;

    public AuthService(PersoneelDAO dao){
        this.dao = dao;
    }

    @Override
    public Optional<Personeel> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Personeel persoon = dao.getByEmailaddress(basicCredentials.getUsername());
        if (persoon != null && persoon.getPassword() != null){
            if(BCrypt.checkpw(basicCredentials.getPassword(), persoon.getWachtwoord())){
                return Optional.of(persoon);
            }
        }
        return Optional.absent();
    }

    @Override
    public boolean authorize(Personeel user, String roleName)
    {
        return user.getRechten().equals(roleName);
    }
}
