package com.Projekat.repository;

import com.Projekat.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM ACCOUNTS WHERE ACCOUNTS.username=?1")
    public Account findByUsername(String username);

    public Optional<Account> findById(Integer id);

    public Page<Account> findAll(Pageable pageable);

    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET ACCOUNTS.password = ?2 WHERE ACCOUNTS.id=?1 ")
    public void updatePassword(Integer acc_id, String newPassword);

    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET ACCOUNTS.username = ?2 WHERE ACCOUNTS.id=?1 ")
    public void updateUsername(Integer acc_id, String newUsername);
}
