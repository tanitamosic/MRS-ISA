package com.Projekat.repository;

import com.Projekat.model.reservations.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM RESERVATION WHERE reservation.id=?1")
    Reservation getReservationById(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE service_id=?1 AND (" +
            "((start_date <= ?2 AND ?2 <= end_date) AND " +
            "(start_date <= ?3 AND  ?3 <= end_date))" +
            "OR" +
            "(?2 <= start_date AND start_date <= ?3)" +
            "OR" +
            "(?2 <= end_date AND end_date <= ?3))")
    List<Reservation> getReservationsForServiceBetweenDates(Integer serviceId, LocalDateTime startDate, LocalDateTime endDate);

}
