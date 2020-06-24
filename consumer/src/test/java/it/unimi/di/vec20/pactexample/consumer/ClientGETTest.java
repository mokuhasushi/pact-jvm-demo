package it.unimi.di.vec20.pactexample.consumer;

import au.com.dius.pact.consumer.junit.PactProviderRule;
import org.junit.Rule;

public class ClientGETTest {

  @Rule
  public PactProviderRule provider =
      new PactProviderRule("EmployeeRepositoryProvider", "localhost", 1234, this);
}
