package com.Projekat.repository;

import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    @Query(nativeQuery = true, value="SELECT * FROM RESERVATION WHERE reservation.id=?1")
    Reservation getReservationById(Integer id);


    @Query(nativeQuery = true,
            value="SELECT * FROM RESERVATION AS r INNER JOIN ADVENTURES AS ad ON r.service_id=ad.id " +
                    "WHERE (?1>=r.start_date AND ?1<=r.end_date) " +
                    "OR (?2>=r.start_date AND ?2<=r.end_date) " +
                    "OR (?1<r.start_date AND ?2>r.end_date)")
    List<Reservation> getAdventureReservationsBetweenDates(Timestamp from, Timestamp to);


    @Query(nativeQuery = true,
            value="SELECT * FROM RESERVATION AS r INNER JOIN BOATS AS ad ON r.service_id=ad.id " +
                    "WHERE (?1>=r.start_date AND ?1<=r.end_date) " +
                    "OR (?2>=r.start_date AND ?2<=r.end_date) " +
                    "OR (?1<r.start_date AND ?2>r.end_date)")
    List<Reservation> getBoatReservationsBetweenDates(Timestamp from, Timestamp to);


    @Query(nativeQuery = true,
            value="SELECT * FROM RESERVATION AS r INNER JOIN COTTAGES AS ad ON r.service_id=ad.id " +
                    "WHERE (?1>=r.start_date AND ?1<=r.end_date) " +
                    "OR (?2>=r.start_date AND ?2<=r.end_date) " +
                    "OR (?1<r.start_date AND ?2>r.end_date)")
    List<Reservation> getCottageReservationsBetweenDates(Timestamp from, Timestamp to);


    @Query(nativeQuery = true,
            value="SELECT * FROM RESERVATION AS r INNER JOIN ADVENTURES AS ad ON r.service_id=ad.id " +
                    "WHERE ((?1>=r.start_date AND ?1<=r.end_date) " +
                    "OR (?2>=r.start_date AND ?2<=r.end_date) " +
                    "OR (?1<r.start_date AND ?2>r.end_date)) AND ad.owner_id=?3")
    List<Reservation> getInstructorsReservationsBetweenDates(Timestamp from, Timestamp to, Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE service_id=?1 AND (" +
            "((start_date <= ?2 AND ?2 <= end_date) AND " +
            "(start_date <= ?3 AND  ?3 <= end_date))" +
            "OR" +
            "(?2 <= start_date AND start_date <= ?3)" +
            "OR" +
            "(?2 <= end_date AND end_date <= ?3))")
    List<Reservation> getReservationsForServiceBetweenDates(Integer serviceId, LocalDateTime startDate, LocalDateTime endDate);


    @Query(nativeQuery = true,
            value="SELECT u.id, u.name, u.surname, u.phone FROM USERS AS u " +
                    "INNER JOIN RESERVATION AS r ON u.id=r.client_id " +
                    "INNER JOIN ADVENTURES ON r.service_id=ADVENTURES.id " +
                    "WHERE ADVENTURES.id=?1 ")
    User findIfAdventureIsReserved(Integer adv_id);

    @Query(nativeQuery = true, value="SELECT * FROM RESERVATION AS r INNER JOIN ADVENTURES AS a ON a.id=r.service_id WHERE r.status=2 AND a.owner_id=?1")
    List<Reservation> fetchCompletedReservations(Integer owner_id);

    @Query(nativeQuery = true, value="SELECT * FROM RESERVATION as r INNER JOIN ADVENTURES as a ON r.service_id=a.id WHERE a.id=?1")
    List<Reservation> getAllAdventureReservations(Integer adv_id);
}
