package io.dropwizard.services;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.models.Employee;
import io.dropwizard.persistence.DAO.EmployeeDAO;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService implements Authenticator<BasicCredentials, Employee> {
    private EmployeeDAO dao;

    public AuthService(EmployeeDAO dao){
        this.dao = dao;
    }

    @Override
    public Optional<Employee> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Employee persoon = dao.getByEmailaddress(basicCredentials.getUsername());
        if (persoon != null && persoon.getPassword() != null){
            if(BCrypt.checkpw(basicCredentials.getPassword(), persoon.getWachtwoord())){
                return Optional.of(persoon);
            }
        }
        return Optional.absent();
    }
}
