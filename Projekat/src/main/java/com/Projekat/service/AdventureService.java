package com.Projekat.service;


import com.Projekat.model.services.Adventure;
import com.Projekat.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

}
