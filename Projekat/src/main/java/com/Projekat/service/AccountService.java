package com.Projekat.service;

import java.util.List;

import com.Projekat.dto.AccountDTO;
import com.Projekat.model.Account;
import com.Projekat.model.users.Role;
import com.Projekat.model.users.User;
import com.Projekat.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public Account findByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    public Account findById(Integer id) throws AccessDeniedException {
        return accountRepository.findById(id).orElseGet(null);
    }

    public List<Account> findAll() throws AccessDeniedException {
        return accountRepository.findAll();
    }

    public Account save(AccountDTO userRequest) {
      		Account u = new Account();
      		u.setUsername(userRequest.getUsername());

      		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
     		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
      		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
      		u.setActivated(true);
      		u.setUsername(userRequest.getUsername());

      		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
      		List<Role> roles = roleService.findByName("ROLE_USER");
      		u.setRoles(roles);

      		return this.accountRepository.save(u);
             }

}
