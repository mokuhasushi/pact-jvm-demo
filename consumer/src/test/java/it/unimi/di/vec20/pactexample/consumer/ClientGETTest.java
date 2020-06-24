package it.unimi.di.vec20.pactexample.consumer;



import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.is;

    public class ClientGETTest {

        @Rule
        public PactProviderRule provider = new PactProviderRule("EmployeeRepositoryProvider", "localhost", 1234, this);

        @Pact(provider = "EmployeeRepositoryProvider", consumer = "BirthdayConsumer")
        public RequestResponsePact pact(PactDslWithProvider builder) {
            return builder
                    .given("data count > 0")
                    .uponReceiving("a request for get json data")
                    .path("/provider.json")
                    .method("GET")
                    .willRespondWith()
                    .status(200)
                    .toPact();
        }

        @Test
        @PactVerification("EmployeeRepositoryProvider")
        public void pactWithOurProvider() throws UnirestException {
            Client client = new Client(provider.getUrl());

            assertThat(client.getTest(), is(200));
        }
    }



