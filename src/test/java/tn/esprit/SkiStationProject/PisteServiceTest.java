package tn.esprit.SkiStationProject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.repositories.PisteRepository;
import tn.esprit.SkiStationProject.services.IPisteServices;
import tn.esprit.SkiStationProject.services.PisteServicesImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

 class PisteServiceTest {
    @Mock
    PisteRepository pisteRepository;
    @InjectMocks
    PisteServicesImpl pisteServices;
    @Test
     void retrieveAllPistesTest(){
        List<Piste> pp = pisteRepository.findAll();
        List<Piste> expectedP=pisteServices.retrieveAllPistes();


        assertEquals(pp, expectedP);

    }

}
