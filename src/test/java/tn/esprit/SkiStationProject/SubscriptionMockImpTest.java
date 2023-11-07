package tn.esprit.SkiStationProject;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;
import tn.esprit.SkiStationProject.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class SubscriptionMockImpTest {
    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    public void shouldRetrieveSubscriptionById() {
        // Create sample courses
        Subscription subscription1 = new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY);

        Subscription sub = subscriptionServices.addSubscription(subscription1);

        // Call the service method to retrieve the subscription by ID
        when(subscriptionRepository.findById(sub.getId())).thenReturn(java.util.Optional.of(sub));

        // Call the service method to retrieve the subscription by ID
        Subscription result = subscriptionServices.retrieveSubscriptionById(sub.getId());

        // Assert that the result matches the sample subscription
        assertEquals(sub, result);

        // Print a success message
        System.out.println("shouldRetrieveSubscriptionById succeeded!");
    }

    public void shouldRetrieveSubscriptionByType() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));
        subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));

        when(subscriptionRepository.findByTypeSub(TypeSubscription.MONTHLY)).thenReturn(subscriptions);

        Set<Subscription> result = subscriptionServices.getSubscriptionByType(TypeSubscription.MONTHLY);

        assertEquals(subscriptions, result);

        // Print a success message
        System.out.println("shouldRetrieveSubscriptionByType succeeded!");
    }

    public void shouldRetrieveSubscriptionsByDates() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));
        subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));

        when(subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6))).thenReturn(subscriptions);

        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6));

        assertEquals(subscriptions, result);

        // Print a success message
        System.out.println("shouldRetrieveSubscriptionsByDates succeeded!");
    }
}
