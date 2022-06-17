package com.Projekat.service;

import com.Projekat.dto.ReservationCottageDTO;
import com.Projekat.exception.RequestNotValidException;
import com.Projekat.exception.ServiceNotAvailableException;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.Account;
import com.Projekat.model.reservations.CottageReservation;
import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.reservations.ReservationStatus;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.users.Client;
import com.Projekat.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AdditionalServicesService asService;

    @Autowired
    MyMailSender mailSender;

    private ReservationCottageDTO reservationRequest;
    private Client client;
    private Account account;
    private Cottage cottage;
    private Set<AdditionalService> selectedAdditionalServices = new HashSet<AdditionalService>();
    private Reservation createdReservation;

    public Reservation getById(Integer id) { return reservationRepository.getById(id); }

    public void reservateCottage(ReservationCottageDTO reservationRequest, Client client, Account account, Cottage cottage) {
        this.reservationRequest = reservationRequest;
        this.client = client;
        this.account = account;
        this.cottage = cottage;

        // provere
        checkDates();

        checkIfDatesAreInGoodRangeForService();
        checkForPossibleConflictInReservationDates();
        checkCapacity();
        checkAdditionalServices();
        Double price = calculatePrice();

        saveNewReservation(price);

        // slanje na mejl klijenta
        sendConfirmationMail();

    }

    private void sendConfirmationMail() {
        mailSender.sendSucessfulCottageReservation(account.getUsername(), createSucessfulCottageReservationText());
    }
    private String createSucessfulCottageReservationText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        String start_date = createdReservation.getStartDate().format(formatter);
        String end_date = createdReservation.getEndDate().format(formatter);

        String text = "Pozdrav\n" +
                "\tUspešno ste rezervisali: " + cottage.getName() + "." +
                "\n\tRezervacija je od: " + start_date +
                " do: " + end_date + "" +
                "\nŽelimo Vam lep provod!";

        return text;
    }

    private void checkForPossibleConflictInReservationDates() {
        // provera da ne postoji druga rezervacija u sistemu koja se poklapa sa ovom
        List<Reservation> conflicts = reservationRepository
                .getReservationsForServiceBetweenDates(cottage.getId(),
                        reservationRequest.getStartDate(), reservationRequest.getEndDate());
        if(!(0==conflicts.size()))
            throw new ServiceNotAvailableException("Izabrani termin je već zauzet!");
    }

    private void checkIfDatesAreInGoodRangeForService() {
        // provera da su datumi u dozvoljenom opsegu za tu vikendicu
        if (!(cottage.getAvailabilityStart().isBefore(reservationRequest.getStartDate())))
            throw new ServiceNotAvailableException("Vikendica nije dostupna u odabranom terminu.");
        if (!(cottage.getAvailabilityEnd().isAfter(reservationRequest.getEndDate())))
            throw new ServiceNotAvailableException("Vikendica nije dostupna u odabranom terminu.");
    }

    private void saveNewReservation(Double calculatedPrice) {
        ReservationStatus status = ReservationStatus.BOOKED;        // ovo je uvek ovako

        // pravljenje nove rezervacije
        CottageReservation reservation = new CottageReservation();
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setCapacity(reservationRequest.getCapacity());
        reservation.setPrice(calculatedPrice);
        reservation.setStatus(status);
        reservation.setClient(client);
        reservation.setService(cottage);

        // ubacivanje u Bazu podataka
        reservationRepository.save(reservation);

        createdReservation = reservation;
    }

    private void checkAdditionalServices() {
        if (null == reservationRequest.getAdditionalServices())
            return;
        for(Integer asId : reservationRequest.getAdditionalServices()) {
            AdditionalService as = asService.findById(asId);
            if (null != as)
                selectedAdditionalServices.add(as);
            else
                throw new RequestNotValidException("Izabrane dodatne usluge nisu validne!");
        }
    }

    private Double calculatePrice() {
        // price is calculated by adding basic price and price of each selectedAdditionalServices
        Double calculatedPrice = cottage.getPrice();
        for(AdditionalService as : selectedAdditionalServices) {
            calculatedPrice += as.getPrice();
        }
        return calculatedPrice;
    }

    private void checkCapacity() {
        // capacity mora da bude manji ili jednak onom iz Servisa
        if (null == reservationRequest.getCapacity())
            throw new RequestNotValidException("Kapacitet rezervacije nije unet!");
        if (!(reservationRequest.getCapacity() <= cottage.getNumberOfBeds()))
            throw new RequestNotValidException("Broj osoba ne moze da bude veći od broja kreveta!");
    }

    private void checkDates() {
        // startDate < end Date
        if (null == reservationRequest.getStartDate())
            throw new RequestNotValidException("Datum pocetka rezervacije nije unet!");
        if (null == reservationRequest.getEndDate())
            throw new RequestNotValidException("Datum kraja rezervacije nije unet!");
        if(!(reservationRequest.getStartDate().isBefore(reservationRequest.getEndDate())))
            throw new RequestNotValidException("Datum pocetka rezervacije mora biti pre datuma kraja!");
    }

}
