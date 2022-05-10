package com.Projekat.controller;

import com.Projekat.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/register")
public class RegistrationController {

    @PostMapping(consumes = "application/json", value = "client")
    public ResponseEntity<String> registerClient(@RequestBody UserDTO userDTO) {
        System.out.println("Radi");
        return null;
    }

    @PostMapping(consumes = "application/json", value = "boatowner")
    public ResponseEntity<String> registerBoatowner(@RequestBody UserDTO userDTO) {
        System.out.println("Radi");
        return null;
    }

    @PostMapping(consumes = "application/json", value = "cottageowner")
    public ResponseEntity<String> registerCottageowner(@RequestBody UserDTO userDTO) {
        System.out.println("Radi");
        return null;
    }

    @PostMapping(consumes = "application/json", value = "instructor")
    public ResponseEntity<String> registerInstructor(@RequestBody UserDTO userDTO) {
        System.out.println("Radi");
        return null;
    }



}
