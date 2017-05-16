package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final Map<Integer, Person> people = new HashMap<>();

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Person> getPerson(@PathVariable final int id) {
        if (people.containsKey(id)) {
            return new ResponseEntity<>(people.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public void createPerson(@PathVariable final int id, @RequestBody final Person person) {
        people.put(id, person);
    }

}
