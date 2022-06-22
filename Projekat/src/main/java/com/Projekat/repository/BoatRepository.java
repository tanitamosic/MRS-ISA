package com.Projekat.repository;

import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoatRepository extends JpaRepository<Boat,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE BOATS SET is_deleted=true WHERE id=?1")
    void deleteBoat(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM BOATS WHERE owner_id=?1 AND is_deleted=false")
    Page<Boat> findAllByOwner(Pageable page, Integer owner);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE BOATS SET photo_id=?2 WHERE id=?1")
    void setPrimaryPhoto(Integer id, Integer photo_id);

    @Query(nativeQuery = true, value="SELECT * FROM BOATS WHERE owner_id=?1 AND id=?2 AND is_deleted=false")
    Boat getBoat(Integer ownerId, Integer id);
}
