package com.Projekat.repository;

import com.Projekat.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="INSERT INTO PHOTOS(id, asset_path, service_id) VALUES(nextval('photo_id_seq'), ?1, ?2)")
    void saveNewPhoto(String assetpath, Integer serviceid);

    @Query(nativeQuery = true, value="SELECT PHOTOS.id FROM PHOTOS WHERE PHOTOS.asset_path=?1")
    Integer getPhotoId(String assetpath);
}
