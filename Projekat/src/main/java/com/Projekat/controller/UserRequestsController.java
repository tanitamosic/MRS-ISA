package com.Projekat.controller;

import com.Projekat.dto.AccountDTO;
import com.Projekat.dto.RejectionDTO;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.Account;
import com.Projekat.model.AccountDeletionRequest;
import com.Projekat.model.EmployeeRegistrationRequest;
import com.Projekat.service.AccountService;
import com.Projekat.service.DeletionService;
import com.Projekat.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRequestsController {

    @Autowired
    private MyMailSender mailSender;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DeletionService deletionService;

    // ---------------------------------------------------- DELETION REQUESTS ------------------------------------------

    @GetMapping(value="deletion-requests")
    public ResponseEntity<List<AccountDeletionRequest>> getDeletionRequests() {
        try {
            List<AccountDeletionRequest> data = deletionService.getAccDelRequests();
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (DataAccessException selectException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="accept-deletion")
    public ResponseEntity<String> deleteAccount(@RequestBody AccountDTO acc) {
        try {
            String username = acc.getUsername();
            Integer id = acc.getId();
            accountService.delete(id);
            deletionService.accept(id);
            mailSender.sendAccDeletionMail(username);
            return new ResponseEntity<>("Uspešno ste obrisali nalog korisnika", HttpStatus.OK);
        } catch (DataAccessException selectException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="reject-deletion")
    public ResponseEntity<String> rejectDeletion(@RequestBody RejectionDTO rejection) {
        try {
            Integer acc_id = rejection.getAcc_id();
            String explanation = rejection.getExplanation();
            deletionService.reject(acc_id);
            Account acc = accountService.findById(acc_id);
            mailSender.sendDelRejectMail(acc.getUsername(), explanation);
            return new ResponseEntity<>("Uspešno ste odbili zahtev za registraciju", HttpStatus.OK);
        } catch (DataAccessException selectException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // ---------------------------------------------------- REGISTRATION REQUESTS --------------------------------------

    @GetMapping(value="pendingRegistrations")
    public ResponseEntity<List<EmployeeRegistrationRequest>> getPendingRegistrations() {
        try {
            List<EmployeeRegistrationRequest> response = employeeService.getAllPendingRequests();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException selectException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="accept-request", consumes="application/json")
    public ResponseEntity<String> acceptRegistrationRequest(@RequestBody AccountDTO acc) {
        Integer id = acc.getId();
        accountService.activateAccount(id);
        employeeService.accept(id);
        mailSender.sendRegAcceptMail(acc.getUsername());
        return new ResponseEntity<>("Uspešno ste aktivirali nalog zaposlenog", HttpStatus.OK);
    }

    @PostMapping(value="reject-request", consumes="application/json")
    public ResponseEntity<String> rejectRegistrationRequest(@RequestBody RejectionDTO rejection) {
        Integer acc_id = rejection.getAcc_id();
        String explanation = rejection.getExplanation();
        employeeService.reject(acc_id);
        Account acc = accountService.findById(acc_id);
        mailSender.sendRegRejectMail(acc.getUsername(), explanation);
        return new ResponseEntity<>("Uspešno ste odbili zahtev za registraciju", HttpStatus.OK);
    }

}
