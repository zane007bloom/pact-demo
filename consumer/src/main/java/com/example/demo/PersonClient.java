package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PersonClient extends RestTemplate {

    private final String baseUrl;

    public PersonClient(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<Person> getPerson(final int id) {
        return getForEntity(baseUrl + "/people/" + id, Person.class);
    }

}
