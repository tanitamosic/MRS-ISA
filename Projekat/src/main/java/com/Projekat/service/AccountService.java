package com.Projekat.service;

import com.Projekat.model.Account;
import com.Projekat.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccountByEmail(String email) { return accountRepository.findOneByEmail(email); }

    public void saveNewAccount(Account acc) {
        accountRepository.saveAndFlush(acc);
    }

}
