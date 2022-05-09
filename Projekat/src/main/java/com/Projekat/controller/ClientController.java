package com.Projekat.controller;

import com.Projekat.dto.ClientDTO;
import com.Projekat.dto.CottageDTO;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import com.Projekat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "all")
    public ResponseEntity<List<ClientDTO>> getClients(){
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientsDTO = new ArrayList<>();
        for (Client c : clients){
            clientsDTO.add(new ClientDTO(c));
        }
        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
    }


}
