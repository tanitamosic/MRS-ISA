package com.Projekat.controller;

import com.Projekat.dto.ComplexCottageDTO;
import com.Projekat.dto.CottageDTO;
import com.Projekat.dto.SimpleCottageDTO;
import com.Projekat.model.services.Cottage;
import com.Projekat.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/cottages")
public class CottageController {

    @Autowired
    private CottageService cottageService;

    @GetMapping(value = "all")
    public ResponseEntity<List<CottageDTO>> getAllCottages(){
        List<Cottage> cottages = cottageService.findAll();
        List<CottageDTO> cottagesDTO = new ArrayList<>();
        for (Cottage c : cottages){
            cottagesDTO.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "all/withPagination")
    public Page<SimpleCottageDTO> getCottagesWithPagination(Pageable page) {
        Page<Cottage> pageCottage = cottageService.findAll(page);
        Page<SimpleCottageDTO> pageCottageDTO = pageCottage.map(this::convertToSimpleCottageDTO);
        return pageCottageDTO;
    }
    private SimpleCottageDTO convertToSimpleCottageDTO(Cottage c) {
        return new SimpleCottageDTO(c);
    }

    /*
    private CottageDTO convertToCottageDTO(Cottage cottage) {
        CottageDTO dto = new CottageDTO(cottage);
        return dto;
    }
    */


    @GetMapping(value = "/{id}")
    public ResponseEntity<CottageDTO> getCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @GetMapping(value = "getCottage/{id}")
    public ResponseEntity<ComplexCottageDTO> getComplexCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexCottageDTO(cottage), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cottageDTO) {

        Cottage cottage = new Cottage();
        cottage.setName(cottageDTO.getName());
        cottage.setDescription(cottageDTO.getDescription());
        // cottage.setAddress(cottageDTO.getAddress());
        cottage.setRules(cottageDTO.getRules());
        cottage.setPrice(cottageDTO.getPrice());
        cottage.setCancellationTerms(cottageDTO.getCancellationTerms());
        cottage.setAvailabilityStart(cottageDTO.getAvailabilityStart());
        cottage.setAvailabilityEnd(cottageDTO.getAvailabilityEnd());
        cottage.setDeleted(cottageDTO.getDeleted());
        cottage.setNumberOfRooms(cottageDTO.getNumberOfRooms());
        cottage.setNumberOfBeds(cottageDTO.getNumberOfBeds());

        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<CottageDTO> updateCottage(@RequestBody CottageDTO cottageDTO) {

        // a cottage must exist
        Cottage cottage = cottageService.findOne(cottageDTO.getId());

        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cottage.setName(cottageDTO.getName());
        cottage.setDescription(cottageDTO.getDescription());
        cottage.setAddress(cottageDTO.getAddress());
        cottage.setRules(cottageDTO.getRules());
        cottage.setPrice(cottageDTO.getPrice());
        cottage.setCancellationTerms(cottageDTO.getCancellationTerms());
        cottage.setAvailabilityStart(cottageDTO.getAvailabilityStart());
        cottage.setAvailabilityEnd(cottageDTO.getAvailabilityEnd());
        cottage.setDeleted(cottageDTO.getDeleted());
        cottage.setNumberOfRooms(cottageDTO.getNumberOfRooms());
        cottage.setNumberOfBeds(cottageDTO.getNumberOfBeds());

        cottage = cottageService.save(cottage);
        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        if (cottage != null) {
            cottageService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
