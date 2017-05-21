package com.example.demo.rest;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.example.demo.PersonApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(PactRunner.class)
@Provider("PersonProvider")
// TODO Use the pact broker to share pacts
@PactFolder("pacts")
public class PersonControllerTest {

    private static ConfigurableApplicationContext application;
    private static PersonController personController;

    @TestTarget
    public final Target target = new HttpTarget(1234);

    @BeforeClass
    public static void startApp() {
        application = SpringApplication.run(PersonApplication.class);
        personController = application.getBean(PersonController.class);
    }

    @State("a person with id 1 exists")
    public void personExistsState() {
        final Person person = new Person(1, "some", "guy");
        personController.createPerson(1, person);
    }

    @AfterClass
    public static void stopApp() {
        application.stop();
    }

}