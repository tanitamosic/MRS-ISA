package com.Projekat.service;

import com.Projekat.dto.ClientDTO;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import com.Projekat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private UserRepository userRepository;

    public List<Client> getAllClients(){
        return userRepository.findAllClients();
    }

    public List<User> getAllActiveClients() { return userRepository.getAllActiveClients(); }

}
