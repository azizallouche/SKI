package tn.esprit.SkiStationProject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.entities.enums.Color;
import tn.esprit.SkiStationProject.repositories.PisteRepository;
import tn.esprit.SkiStationProject.services.PisteServicesImpl;

//@SpringBootTest
class PisteTest {

    @Mock
    private PisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testAddPiste() {
        Piste pisteToAdd = new Piste("New Piste", Color.GREEN, 800, 15, new HashSet<>());
        when(pisteRepository.save(pisteToAdd)).thenReturn(new Piste("New Piste", Color.GREEN, 800, 15, new HashSet<>()));

        Piste result = pisteServices.addPiste(pisteToAdd);

        assertNotNull(result);
        assertEquals("New Piste", result.getNamePiste());
        assertEquals(Color.GREEN, result.getColor());
        assertEquals(800, result.getLength());
        assertEquals(15, result.getSlope());
    }
    @Test
    void testRetrieveAllPistes() {
        Set<Piste> pisteSet = new HashSet<>();
        pisteSet.add(new Piste("Piste 1", Color.BLUE, 1000, 20, new HashSet<>()));
        pisteSet.add(new Piste("Piste 2", Color.RED, 1500, 25, new HashSet<>()));
        when(pisteRepository.findAll()).thenReturn(new ArrayList<>(pisteSet));

        List<Piste> result = pisteServices.retrieveAllPistes();

        assertEquals(2, result.size());
    }

    @Test
    void testRemovePiste() {
        Long numPisteToRemove = 1L;
        pisteServices.removePiste(numPisteToRemove);

        verify(pisteRepository, times(1)).deleteById(numPisteToRemove);
    }

    @Test
    void testRetrievePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste("Sample Piste", Color.BLUE, 1200, 18, new HashSet<>());
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste result = pisteServices.retrievePiste(numPiste);

        assertNotNull(result);
        assertEquals("Sample Piste", result.getNamePiste());
        assertEquals(Color.BLUE, result.getColor());
        assertEquals(1200, result.getLength());
        assertEquals(18, result.getSlope());
    }
}