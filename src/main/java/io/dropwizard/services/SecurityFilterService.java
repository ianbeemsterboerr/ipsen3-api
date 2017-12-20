package io.dropwizard.services;

import io.dropwizard.View;
import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.DAO.PersoneelDAO;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilterService implements ContainerRequestFilter{
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX_EMPLOYEE = "personeel";
    private static final String SECURED_URL_PREFIX_ADMIN = "admin";

    private PersoneelDAO dao = new PersoneelDAO();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX_EMPLOYEE)){
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
        if(authHeader != null && authHeader.size()>0) {
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
            String decodeString = Base64.decodeAsString(authToken);
            StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
            String email = tokenizer.nextToken();
            String password = tokenizer.nextToken();

            Personeel user = dao.getByEmailaddress(email);

            if (user.getWachtwoord().equals(password)) {
                return;
            }

        }
        giveUnauthorizedResponse(requestContext);

        }
    }

    public void giveUnauthorizedResponse(ContainerRequestContext requestContext){
        Response unauthorizedStatus = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("User can not access the resource")
                .build();

        requestContext.abortWith(unauthorizedStatus);

    }
}
