package com.Projekat.service;

import com.Projekat.model.services.Cottage;
import com.Projekat.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CottageService {

    @Autowired
    private CottageRepository cottageRepository;

    public Cottage findOne(Integer id) {
        return cottageRepository.findById(id).orElseGet(null);
    }

    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    public Page<Cottage> getCottagesWithPagination(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return findAll(page);
    }

    public Page<Cottage> findAll(Pageable page) {
        return cottageRepository.findAll(page);
    }

    public Cottage save(Cottage cottage) {
        return cottageRepository.save(cottage);
    }

    public void remove(Integer id) {
        cottageRepository.deleteById(id);
    }
}
