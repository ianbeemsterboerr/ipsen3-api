package io.dropwizard;

import io.dropwizard.resources.PersoneelResource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApiApplication extends Application<ApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "lala";
    }

    @Override
    public void initialize(final Bootstrap<ApiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ApiConfiguration configuration,
                    final Environment environment) {
        final PersoneelResource personeel = new PersoneelResource();
        environment.jersey().register(personeel);
    }

}
