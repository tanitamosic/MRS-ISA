package com.Projekat.service;

import com.Projekat.model.services.AdditionalService;
import com.Projekat.repository.AdditionalServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdditionalServicesService {

    @Autowired
    private AdditionalServicesRepository asRepo;

    public AdditionalService findById(Integer id) {
        return asRepo.findById(id).orElse(null);
    }

}
