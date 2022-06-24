package com.Projekat.repository;

import com.Projekat.model.services.Subscription;
import com.Projekat.model.users.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query(nativeQuery = true, value="SELECT u.id FROM subscriptions INNER JOIN users u on subscriptions.client_id = u.id where subscriptions.service_id=?1")
    List<Client> getSubscribedClients(Integer service_id);

    @Query(nativeQuery = true, value = "SELECT * FROM subscriptions WHERE subscriptions.client_id=?1 AND subscriptions.service_id=?2")
    Subscription getSubscriptionByClientIdAndServiceId(int cId, int sId);


    @Query(nativeQuery = true, value = "SELECT * FROM subscriptions WHERE subscriptions.client_id=?1")
    Page<Subscription> getAllClientSubscriptions(Integer clientId, Pageable page);

}
