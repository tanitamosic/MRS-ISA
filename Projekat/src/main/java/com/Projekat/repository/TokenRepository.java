package com.Projekat.repository;

import com.Projekat.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM verification_token WHERE token=?1")
    VerificationToken getToken(String token);
}
