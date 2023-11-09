package tn.esprit.SkiStationProject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.SkiStationProject.entities.Instructor;
import tn.esprit.SkiStationProject.repositories.InstructorRepository;
import tn.esprit.SkiStationProject.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



    public class InstructorServicesImplTest {
        @Mock
        private InstructorRepository instructorRepository;
        @InjectMocks
        private InstructorServicesImpl instructorService;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);

            // Test data setup
            //ngrokkkk
            Instructor instructor1 = new Instructor();
            instructor1.setFirstName("farah");
            instructor1.setLastName("braiki");

            Instructor instructor2 = new Instructor();
            instructor2.setFirstName("rim");
            instructor2.setLastName("chaouch");

            // Mock behavior for the repository
            when(instructorRepository.findAll()).thenReturn(Arrays.asList(instructor1, instructor2));
            when(instructorRepository.save(any(Instructor.class))).thenAnswer(invocation -> invocation.getArgument(0));
        }

        @Test
        public void testRetrieveAllInstructors() {
            List<Instructor> instructors = instructorService.retrieveAllInstructors();

            assertEquals(2, instructors.size());
            assertTrue(instructors.stream().anyMatch(i -> i.getFirstName().equals("farah")));
            assertTrue(instructors.stream().anyMatch(i -> i.getFirstName().equals("braiki")));
        }

        // Add and modify other test methods as needed
    }


