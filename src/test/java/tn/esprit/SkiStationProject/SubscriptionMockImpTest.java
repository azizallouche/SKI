package tn.esprit.SkiStationProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;  // Add this import
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;  // Add this import
import org.springframework.test.context.junit4.SpringRunner;  // Add this import
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;
import tn.esprit.SkiStationProject.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)  // Add this annotation
@SpringJUnitConfig(classes = SubscriptionMockImpTest.class)  // Add this annotation
@SpringBootTest
@ActiveProfiles("test")
public class SubscriptionMockImpTest {
    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldRetrieveSubscriptionsByDates() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));
        subscriptions.add(new Subscription(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));

        when(subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6))).thenReturn(subscriptions);

        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6));

        assertEquals(subscriptions, result);

        System.out.println("shouldRetrieveSubscriptionsByDates succeeded!");
    }
}
