package it.unimi.di.vec20.pactexample.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import io.dropwizard.testing.ResourceHelpers;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import io.dropwizard.testing.junit.DropwizardAppRule;

@RunWith(PactRunner.class)
@Provider("EmployeeRepositoryProvider")
@PactFolder("build/pacts")
public class PactProviderVerification {
    @ClassRule
    public static final DropwizardAppRule<EmployeeServiceConfig> RULE = new DropwizardAppRule<EmployeeServiceConfig>(EmployeeRepositoryApplication.class,
            ResourceHelpers.resourceFilePath("main-app-config.yaml"));

    @TestTarget
    public final Target target = new HttpTarget(8080);

    @State("data count > 0")
    public void dataCountGreaterThanZero() {
    }

    @State("user john exists")
    public void johnexists () {

    }
}