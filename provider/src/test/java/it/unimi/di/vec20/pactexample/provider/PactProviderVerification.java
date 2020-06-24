package it.unimi.di.vec20.pactexample.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("EmployeeRepositoryProvider")
@PactFolder("build/pacts")
public class PactProviderVerification {

  @SuppressWarnings("deprecation") // Deprecated because of Junit5
  @ClassRule
  public static final DropwizardAppRule<EmployeeServiceConfig> RULE =
      new DropwizardAppRule<>(
          EmployeeRepositoryApplication.class,
          ResourceHelpers.resourceFilePath("main-app-config.yaml"));

  @TestTarget public final Target target = new HttpTarget(8080);

  @State("no employees born on date")
  public void noEmployeesFound() {}

  @State("employee johndoe exists")
  public void johnExists() {
    EmployeeRepositoryDataStore.INSTANCE.addEmployee(
        new Employee("doe", "john", 1985, 6, 23, "john.doe@foobar.com"));
  }
}
