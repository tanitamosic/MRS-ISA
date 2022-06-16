package com.Projekat.service;

import com.Projekat.model.reservations.Reservation;
import com.Projekat.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation getById(Integer id) { return reservationRepository.getById(id); }
}
