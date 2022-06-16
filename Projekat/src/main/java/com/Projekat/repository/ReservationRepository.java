package com.Projekat.repository;

import com.Projekat.model.reservations.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM RESERVATION WHERE reservation.id=?1")
    Reservation getReservationById(Integer id);
}
