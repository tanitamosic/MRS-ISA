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

    public String getUserRole(String username) { return userRepository.getUserRole(username); }

    public User getUserData(String username) { return userRepository.getUserData(username); }

    public Integer findUserAccountId(Integer user_id) { return userRepository.findUserAccountId(user_id); }

    public void updateUserProfile(Integer user_id, String name, String surname, String phone) { userRepository.updateUserProfile(user_id, name, surname, phone); }
    public void updateUserAddress(Integer user_id, Integer address_id) { userRepository.updateUserAddress(user_id, address_id); }
    public User findUserById(Integer id) { return userRepository.findOneById(id); }
}
