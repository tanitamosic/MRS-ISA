package com.Projekat.controller;

import javax.servlet.http.HttpServletResponse;

import com.Projekat.dto.AccountDTO;
import com.Projekat.dto.JwtAuthenticationRequest;
import com.Projekat.dto.AccountTokenState;
import com.Projekat.exception.ResourceConflictException;
import com.Projekat.model.Account;
import com.Projekat.model.users.User;
import com.Projekat.service.AccountService;
import com.Projekat.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

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

        return ResponseEntity.ok(new AccountTokenState(jwt, expiresIn));
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

}