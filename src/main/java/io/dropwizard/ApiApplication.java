package io.dropwizard;


import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.models.Personeel;
import io.dropwizard.persistence.ConnectionPool;
import io.dropwizard.resources.CustomerResource;
import io.dropwizard.resources.LogInResource;
import io.dropwizard.resources.PersoneelResource;
import io.dropwizard.resources.UrenResource;
import io.dropwizard.services.AuthService;
import io.dropwizard.services.SecurityFilterService;
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

        final PersoneelResource personeelResource = new PersoneelResource();
        final UrenResource urenResource = new UrenResource();
        final SecurityFilterService security = new SecurityFilterService();
        final LogInResource logInResource = new LogInResource();
        final CustomerResource customerResource = new CustomerResource();
        environment.jersey().register(personeelResource);
        environment.jersey().register(urenResource);
        environment.jersey().register(security);
        environment.jersey().register(logInResource);
        environment.jersey().register(customerResource);

        environment.jersey().register(AuthFactory.binder(
                new BasicAuthFactory<>(
                        new AuthService(),
                        "lol",
                        Personeel.class
                )
        ));
    }

}
