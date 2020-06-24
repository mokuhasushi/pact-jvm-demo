package it.unimi.di.vec20.pactexample.provider;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class EmployeeRepositoryApplication extends Application<EmployeeServiceConfig> {
    @Override
    public void run(EmployeeServiceConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new RootResource());
        environment.jersey().register(new NoDataExceptionMapper());
        environment.jersey().register(new QueryParameterRequiredExceptionMapper());
    }

    public static void main(String[] args) throws Exception {
        new EmployeeRepositoryApplication().run("server");
        //TODO: gradle task
    }
}
