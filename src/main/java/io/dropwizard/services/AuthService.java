package io.dropwizard.services;



import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;


public class AuthService implements Authenticator<BasicCredentials, Personeel> {
    PersoneelDAO dao = new PersoneelDAO();
    public AuthService(){

    }
    @Override
    public Optional<Personeel> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Personeel persoon = dao.getByEmailaddress(basicCredentials.getUsername());

        if (persoon != null && persoon.getPassword().equals(basicCredentials.getPassword())){
            return Optional.of(persoon);
        }
        return Optional.absent();
    }
}
