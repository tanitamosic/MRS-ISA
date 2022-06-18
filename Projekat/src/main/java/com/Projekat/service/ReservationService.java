package com.Projekat.service;

import com.Projekat.dto.ReservationDTO;
import com.Projekat.exception.RequestNotValidException;
import com.Projekat.exception.ServiceNotAvailableException;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.Account;
import com.Projekat.model.reservations.*;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
//import com.Projekat.model.services.Service;
import com.Projekat.model.users.Client;
import com.Projekat.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    private ReservationDTO reservationRequest;
    private Client client;
    private Account account;
    private com.Projekat.model.services.Service service;
    private Set<AdditionalService> selectedAdditionalServices = new HashSet<AdditionalService>();
    private Reservation createdReservation;


    public void reservateCottage(ReservationDTO reservationRequest, Client client, Account account, Cottage cottage) {
        this.reservationRequest = reservationRequest;
        this.client = client;
        this.account = account;
        this.service = cottage;

        // provere
        checkDates();

        checkIfDatesAreInGoodRangeForService();
        checkForPossibleConflictInReservationDates();
        checkCapacityForCottage(cottage.getNumberOfBeds());
        checkAdditionalServices();
        Double price = calculatePrice();

        saveNewCottageReservation(price);

        // slanje na mejl klijenta
        sendConfirmationMail(createSucessfulReservationText());

    }


    public void reservateBoat(ReservationDTO reservationRequest, Client client, Account account, Boat boat) {
        this.reservationRequest = reservationRequest;
        this.client = client;
        this.account = account;
        this.service = boat;

        // provere
        checkDates();

        checkIfDatesAreInGoodRangeForService();
        checkForPossibleConflictInReservationDates();
        checkCapacityForBoat(boat.getCapacity());
        checkAdditionalServices();
        Double price = calculatePrice();

        saveNewBoatReservation(price);

        // slanje na mejl klijenta
        sendConfirmationMail(createSucessfulReservationText());
    }


    public void reservateAdventure(ReservationDTO reservationRequest, Client client, Account account, Adventure adventure) {
        this.reservationRequest = reservationRequest;
        this.client = client;
        this.account = account;
        this.service = adventure;

        // provere
        checkDates();

        checkIfDatesAreInGoodRangeForService();
        checkForPossibleConflictInReservationDates();
        checkCapacityForAdventure(adventure.getCapacity());
        checkAdditionalServices();
        Double price = calculatePrice();

        saveNewAdventureReservation(price);

        // slanje na mejl klijenta
        sendConfirmationMail(createSucessfulReservationText());
    }



    private void sendConfirmationMail(String text) {
        mailSender.sendSucessfulCottageReservation(account.getUsername(), text);
    }
    private String createSucessfulReservationText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        String start_date = createdReservation.getStartDate().format(formatter);
        String end_date = createdReservation.getEndDate().format(formatter);

        String text = "Pozdrav\n" +
                "\tUspešno ste rezervisali: " + service.getName() + "." +
                "\n\tRezervacija je od: " + start_date +
                " do: " + end_date + "" +
                "\nŽelimo Vam lep provod!";

        return text;
    }


    private void checkForPossibleConflictInReservationDates() {
        // provera da ne postoji druga rezervacija u sistemu koja se poklapa sa ovom
        List<Reservation> conflicts = reservationRepository
                .getReservationsForServiceBetweenDates(service.getId(),
                        reservationRequest.getStartDate(), reservationRequest.getEndDate());
        if(!(0==conflicts.size()))
            throw new ServiceNotAvailableException("Izabrani termin je već zauzet!");
    }

    private void checkIfDatesAreInGoodRangeForService() {
        // provera da su datumi u dozvoljenom opsegu za taj servis
        if (!(service.getAvailabilityStart().isBefore(reservationRequest.getStartDate())))
            throw new ServiceNotAvailableException("Servis nije dostupan u odabranom terminu.");
        if (!(service.getAvailabilityEnd().isAfter(reservationRequest.getEndDate())))
            throw new ServiceNotAvailableException("Servis nije dostupan u odabranom terminu.");
    }

    private void saveNewCottageReservation(Double calculatedPrice) {
        ReservationStatus status = ReservationStatus.BOOKED;        // ovo je uvek ovako

        // pravljenje nove rezervacije
        CottageReservation reservation = new CottageReservation();
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setCapacity(reservationRequest.getCapacity());
        reservation.setPrice(calculatedPrice);
        reservation.setStatus(status);
        reservation.setClient(client);
        reservation.setService(service);
        reservation.setAdditionalServices(selectedAdditionalServices);

        // ubacivanje u Bazu podataka
        reservationRepository.save(reservation);

        createdReservation = reservation;
    }

    private void saveNewBoatReservation(Double calculatedPrice) {
        ReservationStatus status = ReservationStatus.BOOKED;        // ovo je uvek ovako

        // pravljenje nove rezervacije
        BoatReservation reservation = new BoatReservation();
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setCapacity(reservationRequest.getCapacity());
        reservation.setPrice(calculatedPrice);
        reservation.setStatus(status);
        reservation.setClient(client);
        reservation.setService(service);
        reservation.setAdditionalServices(selectedAdditionalServices);

        // ubacivanje u Bazu podataka
        reservationRepository.save(reservation);

        createdReservation = reservation;
    }

    private void saveNewAdventureReservation(Double calculatedPrice) {
        ReservationStatus status = ReservationStatus.BOOKED;        // ovo je uvek ovako

        // pravljenje nove rezervacije
        AdventureReservation reservation = new AdventureReservation();
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setCapacity(reservationRequest.getCapacity());
        reservation.setPrice(calculatedPrice);
        reservation.setStatus(status);
        reservation.setClient(client);
        reservation.setService(service);
        reservation.setAdditionalServices(selectedAdditionalServices);

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

    private double calculatePrice() {
        // price is calculated by adding basic price and price of each selectedAdditionalServices
        Double calculatedPrice = service.getPrice();
        for(AdditionalService as : selectedAdditionalServices) {
            calculatedPrice += as.getPrice();
        }
        return calculatedPrice;
    }

    private void checkCapacityForCottage(Integer numberOfBeds) {
        // capacity mora da bude manji ili jednak onom iz Servisa
        if (null == reservationRequest.getCapacity())
            throw new RequestNotValidException("Kapacitet rezervacije nije unet!");
        if (!(reservationRequest.getCapacity() <= numberOfBeds))
            throw new RequestNotValidException("Broj osoba ne moze da bude veći od broja kreveta!");
    }

    private void checkCapacityForBoat(Integer serviceCapacity) {
        // capacity mora da bude manji ili jednak onom iz Servisa
        if (null == reservationRequest.getCapacity())
            throw new RequestNotValidException("Kapacitet rezervacije nije unet!");
        if (!(reservationRequest.getCapacity() <= serviceCapacity))
            throw new RequestNotValidException("Broj osoba ne moze da bude veći od kapaciteta Broda!");
    }

    private void checkCapacityForAdventure(Integer serviceCapacity) {
        // capacity mora da bude manji ili jednak onom iz Servisa
        if (null == reservationRequest.getCapacity())
            throw new RequestNotValidException("Kapacitet rezervacije nije unet!");
        if (!(reservationRequest.getCapacity() <= serviceCapacity))
            throw new RequestNotValidException("Broj osoba ne moze da bude veći od kapaciteta Avanture!");
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
