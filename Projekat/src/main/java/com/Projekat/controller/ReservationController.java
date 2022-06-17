package com.Projekat.controller;


import com.Projekat.dto.CutDTO;
import com.Projekat.model.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @PostMapping(value="/admin/change-cut")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeCut(@RequestBody CutDTO newCut) {
        if (!Service.setCut(newCut.getCut())) {
            return new ResponseEntity<>("Cut nije u pravom opsegu", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Uspešno ste promenili cut", HttpStatus.OK);
    }

    @GetMapping(value="/admin/get-cut")
    public ResponseEntity<Integer> getCut() {
        return new ResponseEntity<>(Service.getCut(), HttpStatus.OK);
    }

}
