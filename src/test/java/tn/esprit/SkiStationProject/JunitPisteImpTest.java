package tn.esprit.SkiStationProject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.entities.enums.Color;
import tn.esprit.SkiStationProject.services.PisteServicesImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
@SpringBootTest

@Transactional // Rollback transactions after each test
 class JunitPisteImpTest {
    @Autowired
    private PisteServicesImpl pisteServices;

    @Test
     void testRetrieveAllPistes() {
        // Create and save sample courses to the H2 database
        Piste piste1 = new Piste("aziz", Color.GREEN, 56, 23);
        Piste piste2 = new Piste("dddd", Color.BLACK, 80, 90);
        pisteServices.addPiste(piste1);
        pisteServices.addPiste(piste2);

        // Call the service method to retrieve all courses
        List<Piste> pistes =pisteServices.retrieveAllPistes();

        // Assert that the list of courses is not empty and contains the expected courses
        assertNotNull(pistes);
        assertEquals(2, pistes.size());
        assertEquals("aziz", pistes.get(0).getNamePiste());
        assertEquals(Color.GREEN, pistes.get(0).getColor());
        assertEquals(56, pistes.get(0).getLength());
        assertEquals(23, pistes.get(0).getSlope());

        assertEquals("dddd", pistes.get(0).getNamePiste());
        assertEquals(Color.BLACK, pistes.get(0).getColor());
        assertEquals(80, pistes.get(0).getLength());
        assertEquals(90, pistes.get(0).getSlope());



        System.out.println("Test 'Get All Piste' successfully.");
    }

    @Test
     void testAddPiste() {
        // Create a new course
        Piste newpiste = new Piste("piste3", Color.GREEN, 6, 3);

        // Add the new course using the service
        Piste addedPiste = pisteServices.addPiste(newpiste);

        // Retrieve the added course from the database
        Piste retrievedPiste = pisteServices.retrievePiste(addedPiste.getId());

        // Assert that the retrieved course matches the added course
        assertNotNull(addedPiste);
        assertEquals(newpiste.getNamePiste(), addedPiste.getNamePiste());
        assertEquals(newpiste.getColor(), addedPiste.getColor());
        assertEquals(newpiste.getLength(), addedPiste.getLength());
        assertEquals(newpiste.getSlope(), addedPiste.getSlope());

        assertNotNull(retrievedPiste);
        assertEquals(addedPiste.getId(), retrievedPiste.getId());
        assertEquals(addedPiste.getNamePiste(), retrievedPiste.getNamePiste());
        assertEquals(addedPiste.getColor(), retrievedPiste.getColor());
        assertEquals(addedPiste.getLength(), retrievedPiste.getLength());
        assertEquals(addedPiste.getSlope(), retrievedPiste.getSlope());

        System.out.println("Test 'Add Piste' completed successfully.");
    }



    @Test
     void testRetrievePiste() {
        // Create and save a sample course to the H2 database
        Piste piste = new Piste("piste3", Color.GREEN, 6, 3);
        Piste addedPiste = pisteServices.addPiste(piste);

        // Retrieve the course by its ID
        Piste retrievedPiste = pisteServices.retrievePiste(addedPiste.getId());

        // Assert that the retrieved course matches the added course
        assertNotNull(retrievedPiste);
        assertEquals(addedPiste.getId(), retrievedPiste.getId());
        assertEquals(addedPiste.getNamePiste(), retrievedPiste.getNamePiste());
        assertEquals(addedPiste.getColor(), retrievedPiste.getColor());
        assertEquals(addedPiste.getLength(), retrievedPiste.getLength());
        assertEquals(addedPiste.getSlope(), retrievedPiste.getSlope());

        // Try to retrieve a course with an invalid ID
        Piste invalidPiste = pisteServices.retrievePiste(766L);

        // Assert that the result is null for an invalid ID
        assertNull(invalidPiste);

        System.out.println("Test 'test Retrieve Piste' completed successfully.");
    }

    @AfterEach
    public void cleanup() {
        // Delete the courses that were created during the tests
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        for (Piste piste : pistes) {
            pisteServices.removePiste(piste.getId());
        }
    }
}
