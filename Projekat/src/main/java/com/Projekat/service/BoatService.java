package com.Projekat.service;

import com.Projekat.model.services.Boat;
import com.Projekat.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Boat> findAllByOwner(Pageable page, Integer owner) {
        return boatRepository.findAllByOwner(page, owner);
    }

    public Page<Boat> getBoatsWithPagination(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return findAll(page);
    }

    public Page<Boat> findAll(Pageable page) {
        return boatRepository.findAll(page);
    }

    public Boat save(Boat boat) {
        return boatRepository.saveAndFlush(boat);
    }

    public void delete(Integer id) {
        boatRepository.deleteBoat(id);
    }

    public Boat getBoat(Integer ownerId, Integer advId) {
        return boatRepository.getBoat(ownerId, advId);
    }

    public void remove(Integer id) {
        boatRepository.deleteBoat(id);
    }

    public Boat saveBoat(Boat boat) {
        return boatRepository.saveAndFlush(boat);
    }

    public void setBoatPrimaryPhoto(Integer serviceId, Integer photoId) {
        boatRepository.setPrimaryPhoto(serviceId, photoId);
    }
}
