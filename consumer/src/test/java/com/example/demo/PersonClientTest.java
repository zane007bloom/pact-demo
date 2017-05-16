package com.example.demo;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonClientTest {

    @Rule
    public PactProviderRule provider = new PactProviderRule("PersonProvider", "localhost", 1234, this);

    @Pact(provider = "PersonProvider", consumer = "PersonConsumer")
    public PactFragment personFragment(final PactDslWithProvider builder) {
        return builder
                .given("a person with id 1 exists")
                .uponReceiving("retrieve person")
                .path("/people/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody()
                        .stringType("name")
                        .stringType("surname")
                        .integerType("id")
                )
                .toFragment();
    }

    @PactVerification("PersonProvider")
    @Test
    public void testArticles() throws IOException {
        final PersonClient cut = new PersonClient("http://localhost:1234");
        final ResponseEntity<Person> response = cut.getPerson(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

}