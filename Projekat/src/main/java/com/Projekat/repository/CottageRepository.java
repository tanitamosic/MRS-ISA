package com.Projekat.repository;

import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CottageRepository extends JpaRepository<Cottage,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE COTTAGES SET is_deleted=true WHERE id=?1")
    void deleteCottage(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM COTTAGES WHERE owner_id=?1 AND is_deleted=false")
    Page<Cottage> findAllByOwner(Pageable page, Integer owner);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE COTTAGES SET photo_id=?2 WHERE id=?1")
    void setAdventurePrimaryPhoto(Integer id, Integer photo_id);

    @Query(nativeQuery = true, value="SELECT * FROM COTTAGES WHERE owner_id=?1 AND id=?2 AND is_deleted=false")
    Cottage getCottage(Integer ownerId, Integer id);

}
