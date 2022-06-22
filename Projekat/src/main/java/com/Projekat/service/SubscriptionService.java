package com.Projekat.service;

import com.Projekat.exception.ServiceDoesNotExistException;
import com.Projekat.exception.SubscritpionAlreadyExistsException;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.services.Subscription;
import com.Projekat.model.users.Client;
import com.Projekat.repository.ServiceRepository;
import com.Projekat.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    ServiceRepository serviceRepository;

    public List<Client> getSubscribedClients(Integer service_id) {return subscriptionRepository.getSubscribedClients(service_id);}

    public void addSubscription(Client client, Integer service_id) {
        com.Projekat.model.services.Service service;
        try {
             service = serviceRepository.findById(service_id).orElseGet(null);
        }
        catch (NullPointerException e) {
            throw new ServiceDoesNotExistException("Izabran Servis ne postoji!");
        }

        checkIfSubscriptionExists(client.getId(), service.getId());

        Subscription sub = new Subscription();
        sub.setService(service);
        sub.setClient(client);
        subscriptionRepository.save(sub);
    }

    private void checkIfSubscriptionExists(Integer clientId, Integer serviceId) {
        Subscription subscription = subscriptionRepository.getSubscriptionByClientIdAndServiceId(clientId, serviceId);
        if (subscription != null) {
            throw new SubscritpionAlreadyExistsException("Već ste pretplaćeni na akcije izabranog Servisa.");
        }
    }
}
