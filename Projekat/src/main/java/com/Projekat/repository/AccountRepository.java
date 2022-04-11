package com.Projekat.repository;

import com.Projekat.model.Account;
import com.Projekat.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Account findOneByEmail(String email);

    public Page<Account> findAll(Pageable pageable);


}
