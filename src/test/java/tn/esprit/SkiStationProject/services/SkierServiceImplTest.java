package tn.esprit.SkiStationProject.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.entities.Registration;
import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.repositories.PisteRepository;
import tn.esprit.SkiStationProject.repositories.RegistrationRepository;
import tn.esprit.SkiStationProject.repositories.SkierRepository;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class SkierServiceImplTest {

    @Autowired
    SkierRepository skierRepository;

    @Autowired
    PisteRepository pisteRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    SkierServicesImpl skierServices;

    @Test
    @Order(1)
    public void testRetrieveAllSkiers() {
        List<Skier> skiers = skierServices.retrieveAllSkiers();
        assertEquals(0, skiers.size()); 
    }

    @Test
    @Order(2)
    public void testAddSkier() {
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2023, 11, 10));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setFirstName("Louay");
        skier.setLastName("Guetat");
        skier.setSubscription(subscription);
        skier = skierServices.addSkier(skier);

        assertNotNull(skier.getId());
    }


    @Test
    @Order(3)
    public void testAssignSkierToSubscription() {
        Subscription subscription = new Subscription(LocalDate.of(2023, 11, 10), LocalDate.of(2024, 11, 9), 600F, TypeSubscription.ANNUAL);
        subscription = subscriptionRepository.save(subscription);
    
        Skier skier = skierServices.assignSkierToSubscription(1L, 1L);
    
        assertEquals(1L, skier.getSubscription().getId());
    }

    @Test
    @Order(4)
    public void testAddSkierAndAssignToCourse() {
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2023, 11, 10));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setFirstName("Louay");
        skier.setLastName("Guetat");
        skier.setSubscription(subscription);

        Registration registration = new Registration(4, null, null);
        registration = registrationRepository.save(registration);
        HashSet<Registration> regHashSet = new HashSet<>();
        regHashSet.add(registration);

        skier.setRegistrations(regHashSet);

        Course course = new Course();
        course = courseRepository.save(course);

        Skier savedSkier = skierServices.addSkierAndAssignToCourse(skier, 1L);

        boolean found = false;
        for (Registration r : savedSkier.getRegistrations()) {
            System.out.println("Hello "+r.getCourse().getId());
            if (r.getCourse().getId() == course.getId()) {
                found = true;
                break;
            }
        }

        assertTrue(found);    
    }

    @Test
    @Order(5)
    public void testRemoveSkier() {
        Skier skier = new Skier();
        skierRepository.save(skier);

        skierServices.removeSkier(3L);

        assertEquals(2, skierServices.retrieveAllSkiers().size());
    }

    @Test
    @Order(6)
    public void testRetrieveSkier() {
        Skier skier = skierServices.retrieveSkier(1L);

        assertEquals(1L, skier.getId());
    }

    @Test
    @Order(7)
    @org.springframework.transaction.annotation.Transactional
    public void testAssignSkierToPiste() {
        Piste piste = new Piste();
        pisteRepository.save(piste);

        skierServices.assignSkierToPiste(1L,1L);
        Skier skier = skierRepository.findById(1L).orElse(null);

        assertEquals(1, skier.getPistes().size());
    }

    @Test
    @Order(8)
    public void testRetrieveSkiersBySubscriptionType() {
        List<Skier> skiers = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        assertEquals(2, skiers.size());
    }

}
