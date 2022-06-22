package com.Projekat.service;

import com.Projekat.dto.ClientReservationReviewDTO;
import com.Projekat.dto.ReservationDTO;
import com.Projekat.exception.*;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.Account;
import com.Projekat.model.reservations.*;
import com.Projekat.model.reservations.submitions.Complaint;
import com.Projekat.model.reservations.submitions.ComplaintStatus;
import com.Projekat.model.reservations.submitions.Review;
import com.Projekat.model.reservations.submitions.ReviewStatus;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
//import com.Projekat.model.services.Service;
import com.Projekat.model.users.Client;
import com.Projekat.repository.ComplaintRepository;
import com.Projekat.repository.ReservationRepository;
import com.Projekat.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation getById(Integer id) { return reservationRepository.getById(id); }

    // FOR ADMIN
    public List<Reservation> getAdventureReservationsBetweenDates(Timestamp from, Timestamp to) {
        return reservationRepository.getAdventureReservationsBetweenDates(from, to);
    }
    public List<Reservation> getBoatReservationsBetweenDates(Timestamp from, Timestamp to) {
        return reservationRepository.getBoatReservationsBetweenDates(from, to);
    }
    public List<Reservation> getCottageReservationsBetweenDates(Timestamp from, Timestamp to) {
        return reservationRepository.getCottageReservationsBetweenDates(from, to);
    }

    // FOR INSTRUCTOR
    public List<Reservation> getInstructorsReservationsBetweenDates(Timestamp from, Timestamp to, Integer id){
        return reservationRepository.getInstructorsReservationsBetweenDates(from, to, id);
    }
    public Client findIfAdventureIsReserved(Integer adv_id) { return reservationRepository.findIfAdventureIsReserved(adv_id); }

    @Autowired
    private AdditionalServicesService asService;

    @Autowired
    MyMailSender mailSender;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    ReviewRepository reviewRepository;

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


    public Page<Reservation> getAllUserReservations(int id, Pageable page){
        return reservationRepository.getAllUserReservations(id, page);
    }

    public Page<Reservation> getAllActiveUserReservations(int id, Pageable page){
        return reservationRepository.getAllActiveUserReservations(id, page);
    }

    public Page<Reservation> getAllHistoricalUserReservations(int id, Pageable page) {
        return reservationRepository.getAllHistoricalUserReservations(id, page);
    }

    public void cancelReservation(Client client, Integer id_reservation) {
        Reservation reservation = null;
        try {
            reservation = reservationRepository.findById(id_reservation).orElseGet(null);
        }
        catch (NullPointerException npe) {
            throw new ReservationDoesNotExistException("Izabrana rezervacija ne postoji u sistemu!");
        }

        if (reservation.getClient().getId() != client.getId()) {
            throw new ReservationOwnerNotAppropriateException("Vi niste napravili izabranu rezervaciju, pa je ne možete ni otkazati!");
        }

        if (reservation.getStatus() != ReservationStatus.BOOKED) {
            throw new ReservationStatusNotAppropriateForCancelationException("Nije moguće otkazati rezervaciju, jer ona trenutno nema status Rezervisana!");
        }

        // provera da li ima barem 3 dana pre početka termina rezervacije
        LocalDateTime reservationStart = reservation.getStartDate();
        LocalDateTime now = LocalDateTime.now();

        long daysBetween = Duration.between(now ,reservationStart).toDays();

        if (daysBetween <= 3) {
            throw new DeadlineForReservationCancellationPassedException("Prošao je rok u kojem je moguće otkazati rezervaciju");
        }

        // promena statusa u BP na CANCELLED
        reservationRepository.cancelReservation(id_reservation);

    }


    public void makeComplaint(Client client, Integer id_reservation, String complaintText) {
        Reservation reservation = null;
        try {
            reservation = reservationRepository.findById(id_reservation).orElseGet(null);
        }
        catch (NullPointerException npe) {
            throw new ReservationDoesNotExistException("Izabrana rezervacija ne postoji u sistemu!");
        }

        if (reservation.getClient().getId() != client.getId()) {
            throw new ReservationOwnerNotAppropriateException("Vi niste napravili izabranu rezervaciju, pa ne možete napraviti žalbu!");
        }

        if (reservation.getStatus() != ReservationStatus.FINISHED) {
            throw new ReservationStatusNotAppropriateException("Nije moguće uložiti žalbu za rezervaciju koja se nije završila.");
        }

        // provera da li rezervacija vec ima complaint
        if (reservation.getComplaint() != null) {
            throw new ComplaintAlreadyExistsException("Već ste napravili žalbu za izabranu rezervaciju. Žalbu je moguće uložiti samo jednom, te ova žalba neće biti evidentirana.");
        }

        // dodavanje zalbe u DB
        Complaint complaint = new Complaint();
        complaint.setReservation(reservation);
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setDescription(complaintText);
        complaintRepository.save(complaint);

        reservationRepository.addComplaintIDToReservation(id_reservation, complaint.getId());


    }

    public void reviewReservation(Client client, ClientReservationReviewDTO crrDto) {
        Reservation reservation = null;
        try {
            reservation = reservationRepository.findById(crrDto.getReservationId()).orElseGet(null);
        }
        catch (NullPointerException npe) {
            throw new ReservationDoesNotExistException("Izabrana rezervacija ne postoji u sistemu!");
        }

        if (reservation.getClient().getId() != client.getId()) {
            throw new ReservationOwnerNotAppropriateException("Vi niste napravili izabranu rezervaciju, pa je ne možete oceniti!");
        }

        if (reservation.getStatus() != ReservationStatus.FINISHED) {
            throw new ReservationStatusNotAppropriateException("Nije moguće uneti ocenu za rezervaciju koja se nije završila.");
        }

        // provera da li rezervacija vec ima review
        if (reservation.getReview() != null) {
            throw new ReviewAlreadyExistsException("Već ste ocenili izabranu rezervaciju. Ocenu je moguće uneti samo jednom, te ova ocena neće biti evidentirana.");
        }

        Review review = new Review();
        review.setRating(crrDto.getRating());
        review.setComment(crrDto.getComment());
        review.setStatus(ReviewStatus.OPEN);
        reviewRepository.save(review);

        reservationRepository.addReviewIDToReservation(reservation.getId(), review.getId());
    }
}
