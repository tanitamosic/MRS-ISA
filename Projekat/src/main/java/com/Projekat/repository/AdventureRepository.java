package com.Projekat.repository;


import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ADVENTURES SET is_deleted=true WHERE id=?1")
    void deleteAdventure(Integer id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ADVENTURES SET photo_id=?2 WHERE id=?1")
    void setAdventurePrimaryPhoto(Integer id, Integer photo_id);

    @Query(nativeQuery = true, value="SELECT * FROM ADVENTURES WHERE owner_id=?1 AND is_deleted=false")
    List<Adventure> getAllInstructorsAdventures(Integer ownerId);

    @Query(nativeQuery = true, value="SELECT * FROM ADVENTURES WHERE owner_id=?1 AND id=?2 AND is_deleted=false")
    Adventure getAdventure(Integer ownerId, Integer id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value="UPDATE ADVENTURES SET " +
                    "name=?1, availability_start=?2, availability_end=?3, cancellation_terms=?4," +
                    "capacity=?5, description=?6, fishing_equipment=?7, price=?8, rules=?9 " +
                    "WHERE id=?10")
    void updateAdventure(String name, LocalDateTime start, LocalDateTime end, String cancel, Integer capacity,
                         String description, String fish, Double price, String rules, Integer advId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE ADVENTURES SET address_id=?1 WHERE id=?2")
    void updateAdventureAddress(Integer addressId, Integer advId);


}
