package com.Projekat.controller;


import com.Projekat.dto.AdventureDTO;
import com.Projekat.model.services.Adventure;
import com.Projekat.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
