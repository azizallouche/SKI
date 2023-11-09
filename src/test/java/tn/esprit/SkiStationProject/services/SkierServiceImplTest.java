package tn.esprit.SkiStationProject.services;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.entities.Registration;
import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.repositories.PisteRepository;
import tn.esprit.SkiStationProject.repositories.RegistrationRepository;
import tn.esprit.SkiStationProject.repositories.SkierRepository;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkierServiceImplTest {

    @Mock
    private SkierRepository skierRepository;

    @Mock
    private PisteRepository pisteRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SkierServicesImpl skierServices;

    @Test
    public void testRetrieveAllSkiers() {
        List<Skier> skiers = skierServices.retrieveAllSkiers();
        assertEquals(0, skiers.size()); 
    }

    @Test
    @Order(1)
    public void testAddSkier() {
        Skier skier = new Skier("Louay", "Guetat", LocalDate.of(1998, 03, 26), "Tunis", null, null, null);
        Skier savedSkier = skierServices.addSkier(skier);

        Skier skierToRemove = new Skier("Ahmed", "Mohsen", LocalDate.of(1998, 03, 26), "Egypte", null, null, null);
        skierServices.addSkier(skierToRemove);

        assertEquals(1L, savedSkier.getId());
    }

    @Test
    @Order(2)
    public void testAssignSkierToSubscription() {
        Subscription subscription = new Subscription(LocalDate.of(2023, 11, 10), LocalDate.of(2024, 11, 9), 600F, TypeSubscription.ANNUAL);
        subscriptionRepository.save(subscription);

        Skier skier = skierServices.assignSkierToSubscription(1L, 1L);

        assertEquals(skier.getSubscription().getId(), subscription.getId());
    }

    @Test
    @Order(3)
    public void testAddSkierAndAssignToCourse() {
        Skier skier = new Skier();
        skier.setRegistrations(new HashSet<>());

        Course course = new Course();
        Long numCourse = 1L;

        when(skierRepository.save(skier)).thenReturn(skier);
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));

        Skier savedSkier = skierServices.addSkierAndAssignToCourse(skier, numCourse);

        assertEquals(course, savedSkier.getRegistrations().iterator().next().getCourse());
        assertEquals(skier, savedSkier.getRegistrations().iterator().next().getSkier());

        verify(registrationRepository, times(1)).save(any(Registration.class));         
    }

    @Test
    @Order(4)
    public void testRemoveSkier() {
        skierServices.removeSkier(2L);

        assertEquals(2, skierServices.retrieveAllSkiers().size());
    }

    @Test
    @Order(5)
    public void testRetrieveSkier() {
        Skier skier = skierServices.retrieveSkier(1L);

        assertEquals(1L, skier.getId());
    }

    @Test
    @Order(6)
    public void testAssignSkierToPiste() {
        Piste piste = new Piste();
        pisteRepository.save(piste);
        Skier skier = skierServices.assignSkierToPiste(1L,1L);

        assertEquals(1, skier.getPistes().size());
    }

    @Test
    @Order(7)
    public void testRetrieveSkiersBySubscriptionType() {
        List<Skier> skiers = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        assertEquals(1, skiers.size());
    }

}
