package com.Projekat.repository;

import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findOneById(Integer id);

    public Page<User> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM USERS U WHERE U.type = 'CL'")
    public List<Client> findAllClients();

    @Query(nativeQuery = true, value="SELECT ROLES.name FROM ROLES INNER JOIN ACCOUNT_ROLE ON ACCOUNT_ROLE.role_id=ROLES.id INNER JOIN ACCOUNTS ON ACCOUNTS.id=ACCOUNT_ROLE.account_id WHERE ACCOUNTS.username=?1")
    public String getUserRole(String username);
    //User findByUsername(String username);
    @Query(nativeQuery = true, value="SELECT * FROM USERS INNER JOIN ACCOUNTS ON ACCOUNTS.user_id=USERS.id WHERE ACCOUNTS.username=?1")
    public User getUserData(String username);

    @Query(nativeQuery = true, value="SELECT ACCOUNTS.id FROM USERS INNER JOIN  ACCOUNTS ON ACCOUNTS.user_id=USERS.id WHERE ACCOUNTS.user_id=?1")
    public Integer findUserAccountId(Integer user_id);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE USERS SET name=?2, surname=?3, phone=?4 WHERE USERS.id=?1")
    public void updateUserProfile(Integer user_id, String name, String surname, String phone);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE USERS SET biography=?2 WHERE USERS.id=?1")
    public void updateInstructorBiography(Integer user_id, String biography);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE USERS SET address_id=?2 WHERE USERS.id=?1")
    void updateUserAddress(Integer user_id, Integer address_id);

    @Query(nativeQuery = true, value="SELECT ACCOUNTS.username FROM USERS INNER JOIN ACCOUNTS ON ACCOUNTS.user_id=USERS.id WHERE ACCOUNTS.user_id=?1")
    String findUsernameById(Integer user_id);

    @Query(nativeQuery = true, value="SELECT * FROM USERS INNER JOIN ACCOUNTS ON ACCOUNTS.user_id=USERS.id INNER JOIN ACCOUNT_ROLE ON ACCOUNTS.id = ACCOUNT_ROLE.account_id WHERE ACCOUNTS.activated=true AND ACCOUNTS.deleted=false AND ACCOUNT_ROLE.role_id=3")
    List<Client> getAllActiveClients();
}
