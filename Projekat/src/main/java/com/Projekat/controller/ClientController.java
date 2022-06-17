package com.Projekat.controller;

import com.Projekat.dto.ClientDTO;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.Account;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import com.Projekat.service.AccountService;
import com.Projekat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MyMailSender mailSender;

    @GetMapping(value = "/clients/all")
    public ResponseEntity<List<ClientDTO>> getClients(){
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientsDTO = new ArrayList<>();
        for (Client c : clients){
            clientsDTO.add(new ClientDTO(c));
        }
        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
    }

    @GetMapping(value="/admin/get-active-clients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllActiveUsers() {
        List<User> users = clientService.getAllActiveClients();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping(value="/admin/delete-client/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id) {
        Account acc = accountService.findByUserId(id);
        accountService.delete(acc.getId());
        mailSender.sendDeletionNotificationMail(acc.getUsername());
        return new ResponseEntity<>("Uspešno ste obrisali klijenta", HttpStatus.OK);
    }

}
