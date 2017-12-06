package io.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.resources.PersoneelResource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class lalaApplication extends Application<lalaConfiguration> {

    public static void main(final String[] args) throws Exception {
        new lalaApplication().run(args);
    }

    @Override
    public String getName() {
        return "lala";
    }

    @Override
    public void initialize(final Bootstrap<lalaConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final lalaConfiguration configuration,
                    final Environment environment) {
        final PersoneelResource personeel = new PersoneelResource();
        environment.jersey().register(personeel);
    }

}
