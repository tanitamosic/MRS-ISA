package com.Projekat.controller;

import com.Projekat.dto.UserDTO;
import com.Projekat.model.Account;
import com.Projekat.model.Address;
import com.Projekat.model.users.*;
import com.Projekat.service.AccountService;
import com.Projekat.service.AddressService;
import com.Projekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DataAccessException;

@RestController
@RequestMapping(value = "api/register")
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping(consumes = "application/json", value = "client")
    public ResponseEntity<String> registerClient(@RequestBody UserDTO newUser) {
        Client user = new Client();
        user.setName(newUser.Name);
        user.setPhone(newUser.Phone);
        user.setSurname(newUser.Surname);
        Address address = new Address(newUser.Street, newUser.City, newUser.State);
        user.setAddress(address);
        user.setPenalties(0);
        user.setLoyaltyScore(0);

        Account acc = getAccount(newUser, user);

        try {
            addressService.saveNewAddress(address);
            accountService.saveNewAccount(acc);
            userService.saveNewUser(user);
        } catch (DataAccessException saveException) {
            return new ResponseEntity<>("Nalog sa ovim mejlom već postoji!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Registracija uspesna!", HttpStatus.OK);
    }



    @PostMapping(consumes = "application/json", value = "boatowner")
    public ResponseEntity<String> registerBoatowner(@RequestBody UserDTO newUser) {
        BoatOwner user = new BoatOwner();
        user.setName(newUser.Name);
        user.setPhone(newUser.Phone);
        user.setSurname(newUser.Surname);
        Address address = new Address(newUser.Street, newUser.City, newUser.State);
        user.setAddress(address);

        Account acc = getAccount(newUser, user);
        acc.setActivated(false);
        try {
            addressService.saveNewAddress(address);
            accountService.saveNewAccount(acc);
            userService.saveNewUser(user);
        } catch (DataAccessException saveException) {
            return new ResponseEntity<>("Nalog sa ovim mejlom već postoji!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Registracija uspesna! Cekajte na odobrenje administratora za aktivaciju naloga", HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value = "cottageowner")
    public ResponseEntity<String> registerCottageowner(@RequestBody UserDTO newUser) {
        CottageOwner user = new CottageOwner();
        user.setName(newUser.Name);
        user.setPhone(newUser.Phone);
        user.setSurname(newUser.Surname);
        Address address = new Address(newUser.Street, newUser.City, newUser.State);
        user.setAddress(address);

        Account acc = getAccount(newUser, user);
        acc.setActivated(false);
        try {
            addressService.saveNewAddress(address);
            accountService.saveNewAccount(acc);
            userService.saveNewUser(user);
        } catch (DataAccessException saveException) {
            return new ResponseEntity<>("Nalog sa ovim mejlom već postoji!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Registracija uspesna! Cekajte na odobrenje administratora za aktivaciju naloga", HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value = "instructor")
    public ResponseEntity<String> registerInstructor(@RequestBody UserDTO newUser) {
        Instructor user = new Instructor();
        user.setName(newUser.Name);
        user.setPhone(newUser.Phone);
        user.setSurname(newUser.Surname);
        Address address = new Address(newUser.Street, newUser.City, newUser.State);
        user.setAddress(address);
        user.setBiography(null);

        Account acc = getAccount(newUser, user);
        acc.setActivated(false);

        try {
            addressService.saveNewAddress(address);
            accountService.saveNewAccount(acc);
            userService.saveNewUser(user);
        } catch (DataAccessException saveException) {
            return new ResponseEntity<>("Nalog sa ovim mejlom već postoji!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Registracija uspesna! Cekajte na odobrenje administratora za aktivaciju naloga", HttpStatus.OK);
    }

    private Account getAccount(UserDTO newUser, User user) {
        Account acc = new Account();
        acc.setActivated(true);
        acc.setDeleted(false);
        acc.setUsername(newUser.Username);
        acc.setUser(user);
        acc.setPassword(newUser.Password1);
        return acc;
    }

}
