package com.Projekat.service;

import com.Projekat.model.reservations.Reservation;
import com.Projekat.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation getById(Integer id) { return reservationRepository.getById(id); }

    public List<Reservation> getAdventureReservationsBetweenDates(Timestamp from, Timestamp to) {
        return reservationRepository.getAdventureReservationsBetweenDates(from, to);
    }
    public List<Reservation> getBoatReservationsBetweenDates(Timestamp from, Timestamp to) {
        return reservationRepository.getBoatReservationsBetweenDates(from, to);
    }
    public List<Reservation> getCottageReservationsBetweenDates(Timestamp from, Timestamp to) {
        return reservationRepository.getCottageReservationsBetweenDates(from, to);
    }
}
