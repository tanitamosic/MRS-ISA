package com.Projekat.repository;

import com.Projekat.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="INSERT INTO ADDRESSES(id, state, city, street) VALUES(nextval('adr_id'), ?1, ?2, ?3) ON CONFLICT DO NOTHING")
    public void upsertAddress(String state, String city, String street);
    @Query(nativeQuery = true, value="SELECT ADDRESSES.id FROM ADDRESSES WHERE ADDRESSES.state=?1 AND ADDRESSES.city=?2 AND ADDRESSES.street=?3")
    public Integer getAddressId(String state, String city, String street);
}
