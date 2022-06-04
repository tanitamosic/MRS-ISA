package com.Projekat.controller;


import com.Projekat.dto.AdventureDTO;
import com.Projekat.dto.ComplexAdventureDTO;
import com.Projekat.dto.ComplexCottageDTO;
import com.Projekat.dto.SimpleAdventureDTO;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/adventures")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AdventureDTO> saveAdventure(@RequestBody AdventureDTO adventureDTO) {

        Adventure adventure = new Adventure();
        // set attributes
        adventure.setName(adventureDTO.getName());
        //adventure.setAddress(adventureDTO.getAddress());
        adventure.setDescription(adventureDTO.getDescription());
        adventure.setRules(adventureDTO.getRules());
        adventure.setPrice(adventureDTO.getPrice());
        adventure.setCancellationTerms(adventureDTO.getCancellationTerms());
        adventure.setAvailabilityStart(adventureDTO.getAvailabilityStart());
        adventure.setAvailabilityEnd(adventureDTO.getAvailabilityEnd());
        adventure.setDeleted(adventureDTO.getDeleted());
        adventure.setCapacity(adventureDTO.getCapacity());
        adventure.setFishingEquipment(adventureDTO.getFishingEquipment());

        adventure = adventureService.save(adventure);
        return new ResponseEntity<>(new AdventureDTO(adventure), HttpStatus.CREATED);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<SimpleAdventureDTO>> getAllAdventures(){
        List<Adventure> adventures = adventureService.findAll();
        List<SimpleAdventureDTO> adventureDTO = new ArrayList<>();
        for (Adventure a : adventures){
            adventureDTO.add(new SimpleAdventureDTO(a));
        }
        return new ResponseEntity<>(adventureDTO, HttpStatus.OK);
    }

    @GetMapping(value = "all/withPagination")
    public Page<SimpleAdventureDTO> getAdventuresWithPagination(Pageable page) {
        Page<Adventure> pageAdventure = adventureService.findAll(page);
        Page<SimpleAdventureDTO> pageAdventureDTO = pageAdventure.map(this::convertToSimpleAdventureDTO);
        return pageAdventureDTO;
    }
    private SimpleAdventureDTO convertToSimpleAdventureDTO(Adventure a) {
        return new SimpleAdventureDTO(a);
    }


    @GetMapping(value = "getAdventure/{id}")
    public ResponseEntity<ComplexAdventureDTO> getComplexAdventure(@PathVariable Integer id) {

        Adventure adventure = adventureService.findOne(id);

        // cottage must exist
        if (adventure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexAdventureDTO(adventure), HttpStatus.OK);
    }

}
