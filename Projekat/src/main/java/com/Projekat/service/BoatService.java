package com.Projekat.service;

import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import com.Projekat.repository.BoatRepository;
import com.Projekat.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    public Boat findOne(Integer id) {
        return boatRepository.findById(id).orElseGet(null);
    }

    public List<Boat> findAll() {
        return boatRepository.findAll();
    }

    public Page<Boat> findAll(Pageable page) {
        return boatRepository.findAll(page);
    }
}
