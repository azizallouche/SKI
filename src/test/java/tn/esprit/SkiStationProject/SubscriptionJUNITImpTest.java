package tn.esprit.SkiStationProject;

import tn.esprit.SkiStationProject.entities.*;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.SkiStationProject.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test") // Use the "test" profile to configure the H2 in-memory database
@Transactional // Rollback transactions after each test
class SubscriptionJUNITImpTest {
    @Autowired
    private SubscriptionServicesImpl subscriptionServices;

    @Test
     void testRetrieveAllSubscriptions() {
        Subscription subscription1 = new Subscription();
        subscription1.setStartDate(LocalDate.of(2023, 11, 6));
        subscription1.setEndDate(LocalDate.of(2023, 12, 6));
        subscription1.setPrice(220.0f);
        subscription1.setTypeSub(TypeSubscription.MONTHLY);

        Subscription subscription2 = new Subscription();
        subscription2.setStartDate(LocalDate.of(2023, 01, 01));
        subscription2.setEndDate(LocalDate.of(2024, 01, 01));
        subscription2.setPrice(2200.0f);
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        subscriptionServices.addSubscription(subscription1);
        subscriptionServices.addSubscription(subscription2);

        List<Subscription> subscriptions = subscriptionServices.retrieveAllSubscriptions();

        // Assert that the list of subscriptions is not empty and contains the expected subscriptions
        assertNotNull(subscriptions);
        assertEquals(2, subscriptions.size());

        assertEquals(LocalDate.of(2023, 11, 6), subscriptions.get(0).getStartDate());
        assertEquals(LocalDate.of(2023, 12, 6), subscriptions.get(0).getEndDate());
        assertEquals(TypeSubscription.MONTHLY, subscriptions.get(0).getTypeSub());
        assertEquals(220.0f, subscriptions.get(0).getPrice());

        assertEquals(LocalDate.of(2023, 01, 01), subscriptions.get(1).getStartDate());
        assertEquals(LocalDate.of(2024, 01, 01), subscriptions.get(1).getEndDate());
        assertEquals(TypeSubscription.ANNUAL, subscriptions.get(1).getTypeSub());
        assertEquals(2200.0f, subscriptions.get(1).getPrice());

        System.out.println("Test 'testRetrieveAllSubscriptions' completed successfully.");
    }

    @Test
     void testAddSubscription() {
        Subscription subscription3 = new Subscription();
        subscription3.setStartDate(LocalDate.of(2023, 7, 7));
        subscription3.setEndDate(LocalDate.of(2023, 8, 8));
        subscription3.setPrice(220.0f);
        subscription3.setTypeSub(TypeSubscription.MONTHLY);

        Subscription addedSubscription = subscriptionServices.addSubscription(subscription3);
        Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(addedSubscription.getId());

        assertNotNull(addedSubscription);
        assertNotNull(retrievedSubscription);
        assertEquals(retrievedSubscription.getPrice(), addedSubscription.getPrice());
        assertEquals(retrievedSubscription.getStartDate(), addedSubscription.getStartDate());
        assertEquals(retrievedSubscription.getEndDate(), addedSubscription.getEndDate());
        assertEquals(retrievedSubscription.getTypeSub(), addedSubscription.getTypeSub());

        System.out.println("Test 'testAddSubscription' completed successfully.");
    }

    @Test
     void testUpdateSubscription() {
        Subscription subscription4 = new Subscription();
        subscription4.setStartDate(LocalDate.of(2023, 3, 3));
        subscription4.setEndDate(LocalDate.of(2023, 4, 4));
        subscription4.setPrice(220.0f);
        subscription4.setTypeSub(TypeSubscription.MONTHLY);

        Subscription addedSubscription = subscriptionServices.addSubscription(subscription4);

        addedSubscription.setPrice(180.0f);
        Subscription updatedSubscription = subscriptionServices.updateSubscription(addedSubscription);

        Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(updatedSubscription.getId());

        assertNotNull(updatedSubscription);
        assertEquals(180.0f, updatedSubscription.getPrice());
        assertNotNull(retrievedSubscription);
        assertEquals(updatedSubscription.getId(), retrievedSubscription.getId());
        assertEquals(180.0f, retrievedSubscription.getPrice());

        System.out.println("Test 'testUpdateSubscription' completed successfully.");
    }

}
