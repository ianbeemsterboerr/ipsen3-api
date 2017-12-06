package io.dropwizard.services;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.models.Personeel;
import org.eclipse.jetty.server.Authentication;

import java.util.Optional;

public class AuthService implements Authenticator<BasicCredentials, Personeel> {
    @Override
    public Optional<Personeel> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        return null;
    }
}
