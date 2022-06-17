package com.Projekat.repository;

import com.Projekat.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM ACCOUNTS WHERE ACCOUNTS.username=?1")
    public Account findByUsername(String username);

    public Optional<Account> findById(Integer id);

    public Page<Account> findAll(Pageable pageable);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET password = ?2 WHERE ACCOUNTS.id=?1 ")
    public void updatePassword(Integer acc_id, String newPassword);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET last_password_reset_date = ?2 WHERE ACCOUNTS.id=?1 ")
    public void updatePassResetDate(Integer acc_id, Timestamp new_password_reset_date);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET username = ?2 WHERE ACCOUNTS.id=?1 ")
    public void updateUsername(Integer acc_id, String newUsername);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET activated = true WHERE ACCOUNTS.id=?1")
    void activateAccount(Integer acc_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ACCOUNTS SET deleted = true WHERE ACCOUNTS.id=?1")
    void deleteAcc(Integer id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="INSERT INTO account_role(account_id, role_id) VALUES (?1, ?2) ON CONFLICT DO NOTHING")
    void insertAccountRole(Integer acc_id, Integer role_id);

    @Query(nativeQuery = true, value="SELECT * FROM ACCOUNTS INNER JOIN USERS ON ACCOUNTS.user_id=USERS.id WHERE ACCOUNTS.user_id=?1")
    Account findByUserId(Integer usrId);


}
