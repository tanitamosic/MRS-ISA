package com.Projekat.repository;

import com.Projekat.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Account findByUsername(String username);

    public Optional<Account> findById(Integer id);

    public Page<Account> findAll(Pageable pageable);


}
