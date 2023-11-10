// this is a test


package tn.esprit.SkiStationProject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Instructor;
import tn.esprit.SkiStationProject.entities.Registration;
import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.repositories.InstructorRepository;
import tn.esprit.SkiStationProject.repositories.RegistrationRepository;
import tn.esprit.SkiStationProject.repositories.SkierRepository;
import tn.esprit.SkiStationProject.services.RegistrationServicesImpl;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static tn.esprit.SkiStationProject.entities.enums.TypeCourse.COLLECTIVE_CHILDREN;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationTest {

    @InjectMocks
    private RegistrationServicesImpl registrationServices;
    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private SkierRepository skierRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private InstructorRepository instructorRepository;
    @Test
    void testAddRegistrationAndAssignToSkier() {
        Registration registration = new Registration();
        Skier skier = new Skier();
        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

        // Test the service method
        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, 1L); // Assuming 1L is a Skier ID

        assertEquals(skier, result.getSkier(), "Returned Skier should be the same as the input");
    }

    @Test
    void testAssignRegistrationToCourse() {
        // Create a sample Registration and Course
        Registration registration = new Registration();
        Course course = new Course();

        when(registrationRepository.findById(anyLong())).thenReturn(Optional.of(registration));
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

        Registration result = registrationServices.assignRegistrationToCourse(1L, 2L); // Assuming 1L and 2L are Registration and Course IDs

        assertEquals(course, result.getCourse(), "Returned Course should be the same as the input");
        Mockito.verify(registrationRepository, Mockito.times(1)).save(any(Registration.class));
    }

    @Test
    public void testAddRegistrationAndAssignToSkierAndCourse() {
        // Create test data
        Registration registration = new Registration();
        registration.setNumWeek(1);

        Skier skier = new Skier();
        skier.setDateOfBirth(LocalDate.of(2010, 1, 1)); // Set skier's date of birth - younger than 16

        Course course = new Course();
        course.setTypeCourse(COLLECTIVE_CHILDREN); // Set course type

        // Mock repository calls
        Mockito.when(skierRepository.findById(1L)).thenReturn(java.util.Optional.of(skier));
        Mockito.when(courseRepository.findById(1L)).thenReturn(java.util.Optional.of(course));
        Mockito.when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(
                registration.getNumWeek(), skier.getId(), course.getId())).thenReturn(0L);

        // Call the method
        Registration result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);

        // Assert the result
        assertNull(result, "Returned value should be null when age is less than 16");

        // Update the skier's date of birth to be older than 16
        skier.setDateOfBirth(LocalDate.of(1990, 1, 1)); // Set skier's date of birth - older than 16

        // Call the method again with updated skier's date of birth
        result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);

        // Assert the result
        assertNotNull(result, "Returned value should not be null when age is over 16");
        assertEquals(skier, result.getSkier(), "Returned Skier should be the same as the input");
        assertEquals(course, result.getCourse(), "Returned Course should be the same as the input");
    }

    @Test
    void testNumWeeksCourseOfInstructorBySupport() {
        Long numInstructor = 1L;
        Support support = Support.SNOWBOARD;  // Define the desired support type

        // Create a sample course and instructor
        Course course = new Course();
        course.setSupport(support);
        Set<Course> courses = new HashSet<>(Collections.singletonList(course));

        // Create a sample registration and associate it with the course
        Registration registration = new Registration();
        registration.setCourse(course); // Assuming there is a setCourse() method in the Registration class

        course.setRegistrations(new HashSet<>(Collections.singletonList(registration))); // Set the registration for the course

        Instructor instructor = new Instructor();
        instructor.setCourses(courses);

        // Mock the repositories
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(Collections.singletonList(1));

        // Test the service method
        List<Integer> weeks = registrationServices.numWeeksCourseOfInstructorBySupport(numInstructor, support);

        // Assertions
        assertNotNull(instructor.getCourses(), "Instructor has courses");
        assertNotNull(weeks, "The weeks list should not be null");
        assertEquals(1, weeks.size(), "The number of weeks returned should be 1");
        assertEquals(1, weeks.get(0), "The returned week should be 1");
    }
}
