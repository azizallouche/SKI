package tn.esprit.SkiStationProject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Instructor;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.repositories.InstructorRepository;
import tn.esprit.SkiStationProject.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class InstructorServicesImplTest {
    @Mock
    private InstructorRepository instructorRepository;
    @InjectMocks
    private InstructorServicesImpl instructorService;
    @Mock
    private CourseRepository courseRepository;

    @Before
    public void setUp() {
        // Initialisation des annotations Mockito
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddInstructor() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");


        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.addInstructor(instructor);

        // Assert : Vérification des résultats
        assertNotNull(result); // L'instructeur retourné ne doit pas être nul
        assertEquals("farah", result.getFirstName());
        assertEquals("braiki", result.getLastName());
        assertEquals(instructor, result); // L'instructeur retourné doit être le même que celui passé en argument
    }
    @Test
    public void testAddInstructor_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");


        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.addInstructor(instructor);

        // Assert : Vérification des résultats
        assertNotNull(result); // L'instructeur retourné ne doit pas être nul
        assertEquals("faraha", result.getFirstName());
        assertEquals("b", result.getLastName());
        assertNotEquals(instructor, result);
    }
    @Test
    public void testAddInstructor_NotFound() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");


        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(instructorRepository.save(instructor)).thenReturn(null);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.addInstructor(instructor);

        // Assert : Vérification des résultats
        assertNull(result);


    }
    @Test
    public void testRetrieveAllInstructors() {
        // Arrange
        List<Instructor> instructorList = new ArrayList<>(); // Créer une liste d'instructeurs
        instructorList.add(new Instructor(1L,"farah","braiki"));
        instructorList.add(new Instructor(1L,"emna","gharbia"));
        // Mocking : Simuler le comportement de la méthode findAll dans le repository
        when(instructorRepository.findAll()).thenReturn(instructorList);

        // Act : Appel de la méthode à tester
        List<Instructor> result = instructorService.retrieveAllInstructors();
        assertEquals(2, result.size());
        assertEquals("farah", result.get(0).getFirstName());
        assertEquals("braiki", result.get(0).getLastName());
        assertEquals("emna", result.get(1).getFirstName());
        assertEquals("gharbia", result.get(1).getLastName());
        // Assert : Vérification des résultats
        assertNotNull(result); // La liste retournée ne doit pas être nulle
        assertEquals(instructorList, result); // La liste retournée doit être la même que celle du repository simulée
    }
    @Test
    public void testRetrieveAllInstructors_Failure() {
        // Arrange
        List<Instructor> instructorList = new ArrayList<>(); // Créer une liste d'instructeurs
        instructorList.add(new Instructor(1L,"farah","braiki"));
        instructorList.add(new Instructor(1L,"emna","gharbia"));
        // Mocking : Simuler le comportement de la méthode findAll dans le repository
        when(instructorRepository.findAll()).thenReturn(instructorList);

        // Act : Appel de la méthode à tester
        List<Instructor> result = instructorService.retrieveAllInstructors();
        assertNotNull(result);
        assertEquals(1, result.size());

    }
    @Test
    public void testRetrieveInstructor() {
        // Arrange
        Long numInstructor = 1L;
        Instructor instructor = new Instructor(numInstructor,"farah","braiki");

        // Mocking : Simuler le comportement de la méthode findById dans le repository
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.retrieveInstructor(numInstructor);

        // Assert : Vérification des résultats
        assertEquals("farah", result.getFirstName());
        assertEquals("braiki", result.getLastName());
        assertNotNull(result); // L'instructeur retourné ne doit pas être nul
        assertEquals(instructor, result); // L'instructeur retourné doit être le même que celui du repository simulé
    }
    @Test
    public void testRetrieveInstructor_NotFound() {
        // Arrange
        Long numInstructor = 1L;
        Instructor instructor = new Instructor(numInstructor,"farah","braiki");

        // Mocking : Simuler le comportement de la méthode findById dans le repository
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.retrieveInstructor(numInstructor);

        // Assert : Vérification des résultats
        assertNull(result);


    }
    @Test
    public void testUpdateInstructor() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");

        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.updateInstructor(instructor);

        // Assert : Vérification des résultats
        assertNotNull(result); // L'instructeur retourné ne doit pas être nul
        assertEquals("farah", result.getFirstName());
        assertEquals("braiki", result.getLastName());
        assertEquals(instructor, result); // L'instructeur retourné doit être le même que celui passé en argument
    }

    @Test
    public  void testUpdateInstructor_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");

        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.updateInstructor(instructor);

        assertNull(result);

        // Verify that save method was never called on the repository
        verify(instructorRepository, never()).save(instructor);
    }

   /* @Test
    public void testRemoveInstructor() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");

        // Mocking : Simuler le comportement de la méthode deleteById dans le repository
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act : Appel de la méthode à tester
        instructorService.removeInstructor(1L);
        // Assert : Vérification des résultats
        verify(instructorRepository, times(1)).deleteById(1L);
    }
*/
   /* @Test
    public  void testRemoveInstructor_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");

        // Mocking : Simuler le comportement de la méthode deleteById dans le repository
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act : Appel de la méthode à tester
        instructorService.removeInstructor(1L);
        verify(instructorRepository, times(1)).deleteById(2L);
        // Verify that deleteById was never called on the repository
        verify(instructorRepository, never()).deleteById(1L);

    }*/

    @Test
    public  void testAddInstructorAndAssignToCourse() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");
        Course course = new Course();
        Long numCourse = 1L;

        // Mocking : Simuler le comportement de la méthode findById dans le courseRepository
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));

        // Mocking : Simuler le comportement de la méthode save dans le instructorRepository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);

        // Assert : Vérification des résultats
        assertNotNull(result); // L'instructeur retourné ne doit pas être nul
        assertEquals(1, result.getCourses().size());
        assertTrue(result.getCourses().contains(course)); // L'instructeur doit maintenant contenir le cours assigné
    }
    @Test
    public  void testAddInstructorAndAssignToCourse_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"farah","braiki");
        Course course = new Course();
        Long numCourse = 1L;

        // Mocking : Simuler le comportement de la méthode findById dans le courseRepository
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));

        // Mocking : Simuler le comportement de la méthode save dans le instructorRepository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act : Appel de la méthode à tester
        Instructor result = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);

        // Assert : Vérification des résultats
        assertNotNull(result);
        assertSame(instructor, result);
        assertFalse(result.getCourses().contains(course));
        assertEquals(0, result.getCourses().size());


    }

}





