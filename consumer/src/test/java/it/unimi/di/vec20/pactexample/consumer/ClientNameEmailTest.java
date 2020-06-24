package it.unimi.di.vec20.pactexample.consumer;

import au.com.dius.pact.consumer.junit.PactProviderRule;
import org.junit.Rule;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.is;


public class ClientNameEmailTest {

    @Rule
    public PactProviderRule provider = new PactProviderRule("EmployeeRepositoryProvider", "localhost", 1234, this);

    @Pact(provider = "EmployeeRepositoryProvider", consumer = "BirthdayConsumer")
    public RequestResponsePact returnNameEmail (PactDslWithProvider builder) {
        return builder
                .given("data count > 0")
                .uponReceiving("a request for json data")
                .path("/provider.json")
                .query("month=2&day=2")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(
                        new PactDslJsonBody()
                                .stringValue("name", "john")
                                .stringValue("emailaddress", "john.doe@foobar.com")
                )
                .toPact();
    }

    @Test
    @PactVerification("EmployeeRepositoryProvider")
    public void pactReturnNameEmail() throws UnirestException {
        // Set up our HTTP client class
        Client client = new Client(provider.getUrl());

        // Invoke out client
        List<Object> result = client.fetchAndProcessData(2, 2);
        assertThat(result, is(List.of(new String[] {"john", "john.doe@foobar.com"})));

    }

}
