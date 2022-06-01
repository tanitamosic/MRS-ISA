package com.Projekat.controller;

import com.Projekat.dto.*;
import com.Projekat.model.services.Boat;
import com.Projekat.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/boats")
public class BoatController {

    @Autowired
    private BoatService boatService;

    @GetMapping(value = "getBoat/{id}")
    public ResponseEntity<ComplexBoatDTO> getComplexBoat(@PathVariable Integer id) {

        Boat boat = boatService.findOne(id);

        // cottage must exist
        if (boat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexBoatDTO(boat), HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<SimpleBoatDTO>> getAllCottages(){
        List<Boat> boats = boatService.findAll();
        List<SimpleBoatDTO> boatsDTO = new ArrayList<>();
        for (Boat b : boats){
            boatsDTO.add(new SimpleBoatDTO(b));
        }
        return new ResponseEntity<>(boatsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "all/withPagination")
    public Page<SimpleBoatDTO> getCottagesWithPagination(Pageable page) {
        Page<Boat> pageBoat = boatService.findAll(page);
        Page<SimpleBoatDTO> pageBoatDTO = pageBoat.map(this::convertToSimpleBoatDTO);
        return pageBoatDTO;
    }
    private SimpleBoatDTO convertToSimpleBoatDTO(Boat b) {
        return new SimpleBoatDTO(b);
    }

}
