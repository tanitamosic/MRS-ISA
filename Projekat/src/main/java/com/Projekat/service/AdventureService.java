package com.Projekat.service;


import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.repository.AdventureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
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

}
