package it.unimi.di.vec20.pactexample.consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;

public class ClientPactSpec {

  @Rule
  public PactProviderRule provider =
      new PactProviderRule("EmployeeRepositoryProvider", "localhost", 1234, this);

  @Pact(provider = "EmployeeRepositoryProvider", consumer = "BirthdayConsumer")
  public RequestResponsePact invalidMonthDay(PactDslWithProvider builder) {
    return builder
        .uponReceiving("an invalid request")
        .path("/provider.json")
        .method("GET")
        .willRespondWith()
        .status(400)
        .body("{\"error\": \"valid month and day are required\"}")
        .toPact();
  }

  @Test
  @PactVerification(fragment = "invalidMonthDay")
  public void invalidMonthDayTest() throws UnirestException {
    Client client = new Client(provider.getUrl());

    assertThat(client.getTest(), is(400));
  }

  @Pact(provider = "EmployeeRepositoryProvider", consumer = "BirthdayConsumer")
  public RequestResponsePact returnNameEmail(PactDslWithProvider builder) {
    return builder
        .given("employee johndoe exists")
        .uponReceiving("a request for employees born on date")
        .path("/provider.json")
        .query("month=2&day=2")
        .method("GET")
        .willRespondWith()
        .status(200)
        .body(
            new PactDslJsonBody()
                .stringValue("name", "john")
                .stringValue("emailaddress", "john.doe@foobar.com"))
        .toPact();
  }

  @Test
  @PactVerification(fragment = "returnNameEmail")
  public void pactReturnNameEmail() throws UnirestException {
    Client client = new Client(provider.getUrl());

    List<String> result = client.fetchAndProcessData(2, 2);
    assertThat(result, is(List.of(new String[] {"john", "john.doe@foobar.com"})));
  }

  @Pact(provider = "EmployeeRepositoryProvider", consumer = "BirthdayConsumer")
  public RequestResponsePact noEmployeesFound(PactDslWithProvider builder) {
    return builder
        .given("no employees born on date")
        .uponReceiving("a request for employees born on date")
        .path("/provider.json")
        .query("month=2&day=2")
        .method("GET")
        .willRespondWith()
        .status(404)
        .toPact();
  }

  @Test
  @PactVerification(fragment = "noEmployeesFound")
  public void pactNoEmployeesFoundTest() throws UnirestException {
    assertThat(
        new Client(provider.getUrl()).fetchAndProcessData(2, 2), is(CoreMatchers.nullValue()));
  }
}
