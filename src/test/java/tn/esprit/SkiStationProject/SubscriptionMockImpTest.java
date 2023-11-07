package tn.esprit.SkiStationProject;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;
import tn.esprit.SkiStationProject.services.SubscriptionServicesImpl;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class SubscriptionMockImpTest {
    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void shouldretrieveSubscriptionById() {
        // Create sample courses
        Subscription subscription1 = new Subscription(LocalDate.of(2023, 11, 6),LocalDate.of(2023, 12, 6),220.0f, TypeSubscription.MONTHLY);

        Subscription sub=subscriptionServices.addSubscription(subscription1);


        // Call the service method to retrieve all courses
        Subscription result = subscriptionServices.retrieveSubscriptionById(sub.getId());

        // Assert that the result matches the sample courses
        assertEquals(sub, result);

        // Print a success message
        System.out.println("shouldretrieveSubscriptionById succeeded!");
    }

    @BeforeEach
    public void shouldretrieveSubscriptionByType() {

        List<Subscription> Subscriptions = new ArrayList<>();
        Subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6),LocalDate.of(2023, 12, 6),220.0f, TypeSubscription.MONTHLY));
        Subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6),LocalDate.of(2023, 12, 6),220.0f, TypeSubscription.MONTHLY));

        when(subscriptionRepository.findAll()).thenReturn(Subscriptions);
        Set<Subscription> result = subscriptionServices.getSubscriptionByType(TypeSubscription.MONTHLY);
        assertEquals(Subscriptions, result);

        // Print a success message
        System.out.println("shouldretrieveSubscriptionByType succeeded!");
    }
    @BeforeEach
    public void shouldretrieveSubscriptionsByDates() {

        List<Subscription> Subscriptions = new ArrayList<>();
        Subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6),LocalDate.of(2023, 12, 6),220.0f, TypeSubscription.MONTHLY));
        Subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6),LocalDate.of(2023, 12, 6),220.0f, TypeSubscription.MONTHLY));

        when(subscriptionRepository.findAll()).thenReturn(Subscriptions);
        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6),LocalDate.of(2023, 12, 6));
        assertEquals(Subscriptions, result);

        // Print a success message
        System.out.println("shouldretrieveSubscriptionsByDates succeeded!");
    }




}
