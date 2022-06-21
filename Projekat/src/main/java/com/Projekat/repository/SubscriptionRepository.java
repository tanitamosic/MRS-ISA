package com.Projekat.repository;

import com.Projekat.model.services.Subscription;
import com.Projekat.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query(nativeQuery = true, value="SELECT u.id FROM subscriptions INNER JOIN users u on subscriptions.client_id = u.id where subscriptions.service_id=?1")
    List<Client> getSubscribedClients(Integer service_id);

}
