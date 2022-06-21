package com.Projekat.service;

import com.Projekat.model.users.Client;
import com.Projekat.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Client> getSubscribedClients(Integer service_id) {return subscriptionRepository.getSubscribedClients(service_id);}
}
