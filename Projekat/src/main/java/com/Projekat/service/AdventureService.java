package com.Projekat.service;


import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public Adventure saveAdventure(Adventure adventure) {
        return adventureRepository.saveAndFlush(adventure);
    }

    public Page<Adventure> findAll(Pageable page) {
        return adventureRepository.findAll(page);
    }

    public Adventure findOne(Integer id) {
        return adventureRepository.findById(id).orElseGet(null);
    }

    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    public void delete(Integer id) { adventureRepository.deleteAdventure(id); }

    public void setAdventurePrimaryPhoto(Integer serviceId, Integer photoId) { adventureRepository.setAdventurePrimaryPhoto(serviceId, photoId); }

    public List<Adventure> getAllInstructorsAdventures(Integer ownerId) { return adventureRepository.getAllInstructorsAdventures(ownerId); }

    public Adventure getAdventure(Integer ownerId, Integer advId) { return adventureRepository.getAdventure(ownerId, advId); }

    public void updateAdventureAddress(Integer addressId, Integer advId) { adventureRepository.updateAdventureAddress(addressId, advId);}

    public void updateAdventure(String name, LocalDateTime start, LocalDateTime end, String cancel, Integer capacity,
                    String description, String fish, Double price, String rules, Integer advId) {
        adventureRepository.updateAdventure(name, start, end, cancel, capacity, description, fish, price, rules, advId);
    }
}
