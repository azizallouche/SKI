package tn.esprit.SkiStationProject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.repositories.CourseRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CoursesServicesImplTest {
    @Autowired
    ICourseServices courseServices;

    @Autowired
    CourseRepository courseRepository;

    @Test
    @Order(1)
    public void testRetrieveAllCourses(){        
        List<Course> listCourses = courseServices.retrieveAllCourses();
        Assertions.assertEquals(0, listCourses.size()); 
    }

    @Test
    @Order(2)
    public void testAddCourse(){
        Course course = new Course();
        course.setLevel(1);
        course.setPrice(22.04F);
        course.setSupport(Support.SKI);
        course.setTimeSlot(200);
        course.setTypeCourse(TypeCourse.INDIVIDUAL);

        Course savedCourse = courseServices.addCourse(course);
        assertEquals(course.getId(), savedCourse.getId());
    }

    @Test
    @Order(3)
    public void testUpdateCourse() {
        List<Course> listCourses = courseServices.retrieveAllCourses();
        Long id = 0L;
        if (!listCourses.isEmpty()) {
            // Initialize a Random object
            Random random = new Random();
        
            // Generate a random index
            int randomIndex = random.nextInt(listCourses.size());
        
            // Get the Course object at the random index
            Course randomCourse = listCourses.get(randomIndex);
        
            id = randomCourse.getId();
        }
        Course course = courseServices.retrieveCourse(id);
        course.setPrice(780.5F);
        course.setTimeSlot(1000);
        
        Course updated = courseServices.updateCourse(course);
        assertEquals(course.getId(), updated.getId());
    }

    @Test
    @Order(4)
    public void testRetrieveCourse() {
        List<Course> listCourses = courseServices.retrieveAllCourses();
        Long id = 0L;
        if (!listCourses.isEmpty()) {
            // Initialize a Random object
            Random random = new Random();
        
            // Generate a random index
            int randomIndex = random.nextInt(listCourses.size());
        
            // Get the Course object at the random index
            Course randomCourse = listCourses.get(randomIndex);
        
            id = randomCourse.getId();
        }
        Course course = courseServices.retrieveCourse(id);

        assertEquals(id, course.getId());
    }

    @Test
    @Order(5)
    public void testVerifyCrud(){
        List<Course> listCourses = courseServices.retrieveAllCourses();
        Assertions.assertEquals(1, listCourses.size());
    }

    @Test
    @Order(6)
    public void testDeleteAllCourses(){
        courseRepository.deleteAll();

        List<Course> listCourses = courseServices.retrieveAllCourses();
        Assertions.assertEquals(0, listCourses.size());
    }
}
