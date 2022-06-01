package com.Projekat.controller;

import com.Projekat.dto.LoginCredentialsDTO;
import com.Projekat.model.Account;
import com.Projekat.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

    @PostMapping(consumes = "application/json", value = "attempt-login")
    public ResponseEntity<String> attemptLogin(LoginCredentialsDTO credentials) {
        String email = credentials.Email;
        String password = credentials.Password;

        AccountService as = new AccountService();
        Account acc = as.findAccountByEmail(email);
        if (null == acc) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
        if (!password.equals(acc.getPassword())) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
        if (acc.getDeleted()) {
            return new ResponseEntity<>("This account is deleted.", HttpStatus.BAD_REQUEST);
        }
        // TODO: get user info, generate user token and return the token
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
