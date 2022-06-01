package com.Projekat.repository;

import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findOneById(Integer id);

    public Page<User> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM USERS U WHERE U.type = 'CL'")
    public List<Client> findAllClients();

}
