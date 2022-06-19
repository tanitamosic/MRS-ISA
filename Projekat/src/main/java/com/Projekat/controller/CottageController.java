package com.Projekat.controller;

import com.Projekat.dto.ComplexCottageDTO;
import com.Projekat.dto.CottageDTO;
import com.Projekat.dto.SimpleCottageDTO;
import com.Projekat.model.services.Cottage;
import com.Projekat.service.AccountService;
import com.Projekat.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class CottageController {

    @Autowired
    private CottageService cottageService;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/cottages/all")
    public ResponseEntity<List<CottageDTO>> getAllCottages(){
        List<Cottage> cottages = cottageService.findAll();
        List<CottageDTO> cottagesDTO = new ArrayList<>();
        for (Cottage c : cottages){
            cottagesDTO.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cottages/all/withPagination")
    public Page<SimpleCottageDTO> getCottagesWithPagination(Pageable page) {
        Page<Cottage> pageCottage = cottageService.findAll(page);
        Page<SimpleCottageDTO> pageCottageDTO = pageCottage.map(this::convertToSimpleCottageDTO);
        return pageCottageDTO;
    }

    @GetMapping(value = "/cottages/{owner}/withPagination")
    public Page<SimpleCottageDTO> getOwnedCottagesWithPagination(Pageable page, @PathVariable String owner) {
        Integer ownerId = accountService.findByUsername(owner).getId();
        Page<Cottage> pageCottage = cottageService.findAllByOwner(page,ownerId);
        Page<SimpleCottageDTO> pageCottageDTO = pageCottage.map(this::convertToSimpleCottageDTO);
        return pageCottageDTO;
    }

    @PostMapping(value="/cottages/update-cottage-profile")
    public ResponseEntity<CottageDTO> updateCottageProfile(@RequestBody Cottage newCottage){
        Cottage c =cottageService.save(newCottage);
        return new ResponseEntity<CottageDTO>( new CottageDTO(c),HttpStatus.OK);
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


    @GetMapping(value = "/cottages/{id}")
    public ResponseEntity<CottageDTO> getCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @GetMapping(value = "/cottages/getCottage/{id}")
    public ResponseEntity<ComplexCottageDTO> getComplexCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexCottageDTO(cottage), HttpStatus.OK);
    }

    @PostMapping(value="/cottages", consumes = "application/json")
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

    @PutMapping(value="/cottages", consumes = "application/json")
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

    @DeleteMapping(value = "/admin/delete-cottage/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        if (cottage != null) {
            cottageService.remove(id);
            return new ResponseEntity<>("Uspešno ste obrisali vikendicu.",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vikendica ne postoji", HttpStatus.NOT_FOUND);
        }
    }
}
