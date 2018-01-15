package io.dropwizard;


import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.ConnectionPool;
import io.dropwizard.persistence.DAO.*;
import io.dropwizard.resources.*;
import io.dropwizard.services.*;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.sql.*;
import java.util.EnumSet;


public class ApiApplication extends Application<ApiConfiguration> {
    private String apiName;
    private ConfiguredBundle assetsBundle;
    public static void main(final String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public String getName() {
        return apiName;
    }


    @Override
    public void initialize(final Bootstrap<ApiConfiguration> bootstrap) {
        assetsBundle = (ConfiguredBundle) new ConfiguredAssetsBundle("/assets/", "/", "index.html");
        bootstrap.addBundle(assetsBundle);
    }

    @Override
    public void run(final ApiConfiguration configuration,
                    final Environment environment) {
        apiName = configuration.getApiName();
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin, Authorization");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        ConnectionPool connectionPool = new ConnectionPool("org.mariadb.jdbc.Driver", "jdbc:mariadb://localhost:3306:/UrenregistratieDatabase", "root", "ipsen123");

        /**
         * Initialise all the DAO objects.
         */
        CustomerDAO customerDAO = new CustomerDAO(connectionPool);
        PersoneelDAO personeelDAO = new PersoneelDAO(connectionPool);
        ProjectDAO projectDAO = new ProjectDAO(connectionPool);
        SubjectDAO subjectDAO = new SubjectDAO(connectionPool);
        UrenDAO urenDAO = new UrenDAO(connectionPool);

        /**
         * Initialise all the Service objects.
         */

        final AuthService authService = new AuthService(personeelDAO);
        final CustomerService customerService = new CustomerService(customerDAO);
        final PersoneelService personeelService = new PersoneelService(personeelDAO);
        final ProjectService projectService = new ProjectService(projectDAO, customerDAO);
        final SecurityFilterService security = new SecurityFilterService(personeelDAO);
        final SubjectService subjectService = new SubjectService(subjectDAO, projectDAO, customerDAO);
        final UrenService urenService = new UrenService(urenDAO, customerDAO, projectDAO, subjectDAO);

        /**
         * Initialise all the Resource objects.
         */
        final PersoneelResource personeelResource = new PersoneelResource(personeelService);
        final UrenResource urenResource = new UrenResource(urenService);
        final LogInResource logInResource = new LogInResource();
        final CustomerResource customerResource = new CustomerResource(customerService);
        final ProjectResource projectResource = new ProjectResource(projectService);
        final SubjectResource subjectResource = new SubjectResource(subjectService);

        environment.jersey().register(personeelResource);
        environment.jersey().register(urenResource);
        environment.jersey().register(security);
        environment.jersey().register(logInResource);
        environment.jersey().register(customerResource);
        environment.jersey().register(projectResource);
        environment.jersey().register(subjectResource);

        environment.jersey().register(AuthFactory.binder(
                new BasicAuthFactory<>(
                        authService,
                        "lol",
                        Personeel.class
                )
        ));
    }

}
