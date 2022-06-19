package com.Projekat.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.Projekat.dto.AccountDTO;
import com.Projekat.model.Account;
import com.Projekat.model.VerificationToken;
import com.Projekat.model.users.Role;
import com.Projekat.repository.AccountRepository;
import com.Projekat.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private TokenRepository tokenRepository;


    public Account findByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    public Account findById(Integer id) throws AccessDeniedException {
        return accountRepository.findById(id).orElseGet(null);
    }

    public List<Account> findAll() throws AccessDeniedException {
        return accountRepository.findAll();
    }

    public Account save(AccountDTO accRequest) {
        Account u = new Account();
        u.setUsername(accRequest.getUsername());

        // pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
        // treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
        u.setPassword(passwordEncoder.encode(accRequest.getPassword()));

        u.setUsername(accRequest.getUsername());
        if (!u.getPassword().equals(accRequest.getPassword()))
            u.setLastPasswordResetDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        u.setPassword(accRequest.getPassword());
        u.setActivated(accRequest.getActivated());
        u.setDeleted(accRequest.getDeleted());

        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);

        return this.accountRepository.save(u);
    }
    public Account findAccountByEmail(String username) { return accountRepository.findByUsername(username); }



    public void saveNewAccount(Account acc) {
        accountRepository.saveAndFlush(acc);
    }

    public void updatePassword(Integer acc_id, String newPassword) { accountRepository.updatePassword(acc_id, newPassword); }
    public void updateUsername(Integer acc_id, String newUsername) { accountRepository.updateUsername(acc_id, newUsername); }
    public void createVerificationToken(Account acc, String token) {
        VerificationToken myToken = new VerificationToken(token, acc);
        tokenRepository.save(myToken);
    }

    public void activateAccount(Integer acc_id) { accountRepository.activateAccount(acc_id); }

    public void delete(Integer id) { accountRepository.deleteAcc(id); }
}
