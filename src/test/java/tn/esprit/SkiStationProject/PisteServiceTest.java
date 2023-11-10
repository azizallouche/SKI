package tn.esprit.SkiStationProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.Color;
import tn.esprit.SkiStationProject.repositories.PisteRepository;
import tn.esprit.SkiStationProject.services.IPisteServices;
import tn.esprit.SkiStationProject.services.PisteServicesImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
 class PisteServiceTest {
    @Mock
    PisteRepository pisteRepository;
    @InjectMocks
    PisteServicesImpl pisteServices;

   @BeforeEach
   public void setup() {
      MockitoAnnotations.openMocks(this);}
    @Test
     void retrieveAllPistesTest(){
       List<Piste> pistes = new ArrayList<>();


       pistes.add(new Piste("aziz", Color.GREEN, 5, 7));
       pistes.add(new Piste("aaaaaa", Color.RED,  10, 20));
        when(pisteRepository.findAll()).thenReturn(pistes);

        // Call the service method to retrieve all courses
        List<Piste> result = pisteServices.retrieveAllPistes();

        // Verify that the service method called the repository's findAll method
        verify(pisteRepository).findAll();

        // Assert that the result matches the sample courses
        assertEquals(pistes, result);
        System.out.println("success");
    }

}
