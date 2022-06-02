package com.Projekat.controller;

import javax.servlet.http.HttpServletResponse;

import com.Projekat.dto.AccountDTO;
import com.Projekat.dto.JwtAuthenticationRequest;
import com.Projekat.dto.AccountTokenState;
import com.Projekat.dto.UserDTO;
import com.Projekat.exception.ResourceConflictException;
import com.Projekat.model.Account;
import com.Projekat.model.Address;
import com.Projekat.model.users.*;
import com.Projekat.service.AccountService;
import com.Projekat.service.AddressService;
import com.Projekat.service.UserService;
import com.Projekat.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;


    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AccountTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        String u = authenticationRequest.getUsername();
        String p = authenticationRequest.getPassword();
        System.out.println(u + " " + p );
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(u, p));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Account user = (Account) authentication.getPrincipal();

        String jwt = tokenUtils.generateToken(authenticationRequest.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        String role = userService.getUserRole(authenticationRequest.getUsername());
        System.out.println(role);

        return ResponseEntity.ok(new AccountTokenState(jwt, expiresIn, role));
    }

      // Endpoint za registraciju novog korisnika
      @PostMapping("/signup")
    public ResponseEntity<Account> addAccount(@RequestBody AccountDTO userRequest, UriComponentsBuilder ucBuilder) {

          Account existAccount = this.accountService.findByUsername(userRequest.getUsername());

          if (existAccount != null) {
              throw new ResourceConflictException(userRequest.getId(), "Username already exists");
          }

          Account user = this.accountService.save(userRequest);

          return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(consumes = "application/json", value = "register/client")
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



    @PostMapping(consumes = "application/json", value = "register/boatowner")
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

    @PostMapping(consumes = "application/json", value = "register/cottageowner")
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

    @PostMapping(consumes = "application/json", value = "register/instructor")
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