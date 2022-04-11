package com.Projekat.controller;

import com.Projekat.dto.CottageDTO;
import com.Projekat.model.services.Cottage;
import com.Projekat.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<CottageDTO> getCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }


}
