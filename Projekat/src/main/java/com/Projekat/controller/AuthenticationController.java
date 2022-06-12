package com.Projekat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Projekat.dto.JwtAuthenticationRequest;
import com.Projekat.dto.AccountTokenState;
import com.Projekat.dto.UserDTO;
import com.Projekat.events.OnClientRegistrationEvent;
import com.Projekat.events.OnEmployeeRegistrationEvent;
import com.Projekat.model.Account;
import com.Projekat.model.Address;
import com.Projekat.model.VerificationToken;
import com.Projekat.model.users.*;
import com.Projekat.service.*;
import com.Projekat.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


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

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private MessageSource messages;
    @Autowired
    private TokenService tokenService;

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

        Account acc = new Account();
        try {
            acc = accountService.findByUsername(u);
            if (null != acc && !acc.getActivated()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException saveException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String role = userService.getUserRole(authenticationRequest.getUsername());
        String jwt = tokenUtils.generateToken(authenticationRequest.getUsername(), role);
        int expiresIn = tokenUtils.getExpiredIn();

        System.out.println(role);
        AccountTokenState token = new AccountTokenState(jwt, expiresIn, role);
        token.setUser(userService.getUserData(authenticationRequest.getUsername()));

        return ResponseEntity.ok(token);
    }

    @PostMapping(consumes = "application/json", value = "register/client")
    public ResponseEntity<String> registerClient(@RequestBody UserDTO newUser, HttpServletRequest request) {
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
            acc = accountService.findByUsername(acc.getUsername());
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnClientRegistrationEvent(acc,
                    request.getLocale(), appUrl));

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

        return registerEmployee(newUser, user, address, "vlasnik/vlasnica broda");
    }

    @PostMapping(consumes = "application/json", value = "register/cottageowner")
    public ResponseEntity<String> registerCottageowner(@RequestBody UserDTO newUser) {
        CottageOwner user = new CottageOwner();
        user.setName(newUser.Name);
        user.setPhone(newUser.Phone);
        user.setSurname(newUser.Surname);
        Address address = new Address(newUser.Street, newUser.City, newUser.State);
        user.setAddress(address);

        return registerEmployee(newUser, user, address, "vlasnik/vlasnica vikendice");
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

        return registerEmployee(newUser, user, address, "instruktor pecanja");
    }

    private ResponseEntity<String> registerEmployee(UserDTO newUser, User user, Address address, String role) {
        Account acc = getAccount(newUser, user);
        String username = newUser.Username;
        String request = "Gospodin/Gospođa " + user.getName() + " " + user.getSurname() + " žele da se prijave kao " +
                role + ".\nAdresa stanovanja: " + address.toString() + "\nBroj telefona" + user.getSurname();

        try {
            addressService.saveNewAddress(address);
            accountService.saveNewAccount(acc);
            userService.saveNewUser(user);
            eventPublisher.publishEvent(new OnEmployeeRegistrationEvent(acc, request, username));
        } catch (DataAccessException saveException) {
            return new ResponseEntity<>("Nalog sa ovim mejlom već postoji!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Zahtev za registraciju poslat! Cekajte na odobrenje administratora za aktivaciju naloga", HttpStatus.OK);
    }

    private Account getAccount(UserDTO newUser, User user) {
        Account acc = new Account();
        acc.setActivated(false);
        acc.setDeleted(false);
        acc.setUsername(newUser.Username);
        acc.setUser(user);
        acc.setPassword(newUser.Password1);
        return acc;
    }

    @GetMapping(value = "/regitrationConfirm", produces = "text/html")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {


        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return new ResponseEntity<>("<h1>Ne postoji nalog sa ovim aktivacionim linkom</h1>", HttpStatus.OK);
        }

        Account acc = verificationToken.getAccount();
        Calendar cal = Calendar.getInstance();

        if (acc.getActivated()) {
            return new ResponseEntity<>("<h1>Nalog je vec aktiviran.</h1>", HttpStatus.OK);
        }
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>("<h1>Aktivacija neuspesna. Istekao rok za aktivaciju.</h1>", HttpStatus.OK);
        }

        acc.setActivated(true);
        accountService.activateAccount(acc);
        return new ResponseEntity<>("<body><h1>Aktivacija uspesna</h1></body>", HttpStatus.OK);
    }

}