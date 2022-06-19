package com.Projekat.controller;


import com.Projekat.dto.AdventureDTO;
import com.Projekat.dto.ComplexAdventureDTO;
import com.Projekat.dto.ComplexCottageDTO;
import com.Projekat.dto.SimpleAdventureDTO;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;

    @PostMapping(value="/adventures", consumes = "application/json")
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

    @GetMapping(value = "/adventures/all")
    public ResponseEntity<List<SimpleAdventureDTO>> getAllAdventures(){
        List<Adventure> adventures = adventureService.findAll();
        List<SimpleAdventureDTO> adventureDTO = new ArrayList<>();
        for (Adventure a : adventures){
            adventureDTO.add(new SimpleAdventureDTO(a));
        }
        return new ResponseEntity<>(adventureDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/adventures/all/withPagination")
    public Page<SimpleAdventureDTO> getAdventuresWithPagination(Pageable page) {
        Page<Adventure> pageAdventure = adventureService.findAll(page);
        pageAdventure.forEach(new Consumer<Adventure>() {
            @Override
            public void accept(Adventure adventure) {
                if (adventure.getDeleted()) {

                }
            }
        });
        Page<SimpleAdventureDTO> pageAdventureDTO = pageAdventure.map(this::convertToSimpleAdventureDTO);
        return pageAdventureDTO;
    }
    private SimpleAdventureDTO convertToSimpleAdventureDTO(Adventure a) {
        return new SimpleAdventureDTO(a);
    }


    @GetMapping(value = "/adventures/getAdventure/{id}")
    public ResponseEntity<ComplexAdventureDTO> getComplexAdventure(@PathVariable Integer id) {

        Adventure adventure = adventureService.findOne(id);

        // cottage must exist
        if (adventure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexAdventureDTO(adventure), HttpStatus.OK);
    }

    @DeleteMapping(value="/admin/delete-adventure/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAdventure(@PathVariable Integer id) {
        try {
            adventureService.delete(id);
            return new ResponseEntity<>("Uspešno ste obrisali avanturu", HttpStatus.OK);
        } catch (DataAccessException err) {
            return new ResponseEntity<>("Avantura ne postoji", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/instructor/post-adventure-image", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> createAdventure(@RequestParam("file") MultipartFile multipartFile) {
        System.out.println("radi");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
