package com.Projekat.controller;


import com.Projekat.model.reservations.Reservation;
import com.Projekat.service.ProfitService;
import com.Projekat.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfitController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); // defaultan format datepickera u htmlu
    private Timestamp convertToTimestamp(String date) throws ParseException {
        Date parsedDate = dateFormat.parse(date);
        return new java.sql.Timestamp(parsedDate.getTime());
    }


    @Autowired
    private ProfitService profitService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value="/admin/adventure-profits/{date_from}/{date_to}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> getAllAdventureProfits(@PathVariable String date_from, @PathVariable String date_to) {
        try {
            Timestamp from = convertToTimestamp(date_from);
            Timestamp to = convertToTimestamp(date_to);
            List<Reservation> reservations = reservationService.getAdventureReservationsBetweenDates(from, to);
            Integer profit = 0;
            for (Reservation r: reservations) {
                profit += profitService.getCalculatedPriceCut(r.getId());
            }

            return new ResponseEntity<>(profit, HttpStatus.OK);

        } catch (ParseException e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value="/admin/boat-profits/{date_from}/{date_to}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> getAllBoatProfits(@PathVariable String date_from, @PathVariable String date_to) {
        try {
            Timestamp from = convertToTimestamp(date_from);
            Timestamp to = convertToTimestamp(date_to);
            List<Reservation> reservations = reservationService.getBoatReservationsBetweenDates(from, to);
            Integer profit = 0;
            for (Reservation r: reservations) {
                profit += profitService.getCalculatedPriceCut(r.getId());
            }

            return new ResponseEntity<>(profit, HttpStatus.OK);

        } catch (ParseException e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value="/admin/cottage-profits/{date_from}/{date_to}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> getAllCottageProfits(@PathVariable String date_from, @PathVariable String date_to) {
        try {
            Timestamp from = convertToTimestamp(date_from);
            Timestamp to = convertToTimestamp(date_to);
            List<Reservation> reservations = reservationService.getCottageReservationsBetweenDates(from, to);
            Integer profit = 0;
            for (Reservation r: reservations) {
                profit += profitService.getCalculatedPriceCut(r.getId());
            }

            return new ResponseEntity<>(profit, HttpStatus.OK);

        } catch (ParseException | DataAccessException e)  {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value="/instructor/adventure-profits/{dateFrom}/{dateTo}/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Integer> getInstructorProfit(@PathVariable String dateFrom, @PathVariable String dateTo,
                                                       @PathVariable String id) {
        try {
            Timestamp from = convertToTimestamp(dateFrom);
            Timestamp to = convertToTimestamp(dateTo);
            List<Reservation> reservations = reservationService.getInstructorsReservationsBetweenDates(from, to, Integer.parseInt(id));
            Integer profit = 0;
            for (Reservation r: reservations) {
                profit += profitService.getCalculatedPriceCut(r.getId());
            }
            return new ResponseEntity<>(profit, HttpStatus.OK);

        } catch (ParseException e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/bo/boat-profits/{dateFrom}/{dateTo}/{id}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Integer> getBOProfit(@PathVariable String dateFrom, @PathVariable String dateTo,
                                                       @PathVariable String id) {
        try {
            Timestamp from = convertToTimestamp(dateFrom);
            Timestamp to = convertToTimestamp(dateTo);
            List<Reservation> reservations = reservationService.getBOReservationsBetweenDates(from, to, Integer.parseInt(id));
            Integer profit = 0;
            for (Reservation r: reservations) {
                profit += profitService.getCalculatedPriceCut(r.getId());
            }
            return new ResponseEntity<>(profit, HttpStatus.OK);

        } catch (ParseException e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }
}
