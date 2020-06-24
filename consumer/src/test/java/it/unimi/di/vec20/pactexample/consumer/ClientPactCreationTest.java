package it.unimi.di.vec20.pactexample.consumer;

import static org.junit.Assert.assertEquals;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.core.model.RequestResponsePact;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ClientPactCreationTest extends ConsumerPactTest {
  @Override
  protected RequestResponsePact createPact(PactDslWithProvider builder) {
    return builder
        .uponReceiving("a request for employees born today")
        .path("/provider.json")
        .method("GET")
        .willRespondWith()
        .status(400)
        .body("{\"error\": \"valid month and day are required\"}")
        .toPact();
  }

  @Override
  protected String providerName() {
    return "EmployeeRepositoryProvider";
  }

  @Override
  protected String consumerName() {
    return "BirthdayConsumer";
  }

  @Override
  protected void runTest(MockServer mockServer, PactTestExecutionContext context) {
    try {
      assertEquals(400, new Client(mockServer.getUrl()).getTest());
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }
}
