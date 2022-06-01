package com.Projekat.service;

import com.Projekat.model.users.User;
import com.Projekat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveNewUser(User newUser) { userRepository.saveAndFlush(newUser); }
}
