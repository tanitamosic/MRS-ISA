package com.Projekat.repository;

import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.users.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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
            value="SELECT u.name, u.surname, u.id FROM USERS AS u " +
                    "INNER JOIN RESERVATION AS r ON u.id=r.client_id " +
                    "INNER JOIN ADVENTURES ON r.service_id=?1 ")
    Client findIfAdventureIsReserved(Integer adv_id);
    
    @Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE client_id=?1")
    Page<Reservation> getAllUserReservations(int clientID, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE client_id=?1 AND status=0")
    Page<Reservation> getAllActiveUserReservations(int clientId, Pageable page);

    @Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE client_id=?1 AND status=2")
    Page<Reservation> getAllHistoricalUserReservations(int clientId, Pageable page);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE reservation SET status=1 WHERE reservation.id=?1")
    void cancelReservation(int id);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE reservation SET complaint_id=?2 WHERE reservation.id=?1")
    void addComplaintIDToReservation(int r_id, int c_id);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE reservation SET review_id=?2 WHERE reservation.id=?1")
    void addReviewIDToReservation(Integer resId, Integer revId);
}
