package com.Projekat.controller;


import com.Projekat.dto.CutDTO;
import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.Projekat.dto.ReservationDTO;
import com.Projekat.exception.RequestNotValidException;
import com.Projekat.exception.ServiceDoesNotExistException;
import com.Projekat.exception.ServiceNotAvailableException;
import com.Projekat.model.Account;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import com.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {


    @Autowired
    UserService userService;

    @Autowired
    CottageService cottageService;

    @Autowired
    BoatService boatService;

    @Autowired
    AdventureService adventureService;

    @Autowired
    ReservationService reservationService;


    @GetMapping("/instructor/{usr_id}/get-completed-reservations")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Reservation>> getAdventureHistory(@PathVariable Integer usr_id) {
        List<Reservation> reservations =  reservationService.getInstructorsCompletedReservations(usr_id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


    @PostMapping(value="/admin/change-cut")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeCut(@RequestBody CutDTO newCut) {
        if (!Service.setCut(newCut.getCut())) {
            return new ResponseEntity<>("Cut nije u pravom opsegu", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Uspešno ste promenili cut", HttpStatus.OK);
    }

    @GetMapping(value="/admin/get-cut")
    public ResponseEntity<Integer> getCut() {
        return new ResponseEntity<>(Service.getCut(), HttpStatus.OK);
    }


    @PostMapping(value = "/reservation/cottage")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> reserveCottage(@RequestBody ReservationDTO reservationRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            Cottage cottage = cottageService.findOne(reservationRequest.getServiceId());
            if (null == cottage) {
                throw new ServiceDoesNotExistException("Izabrana vikendica ne postoji!");
            }

            reservationService.reservateCottage(reservationRequest, client, account,cottage);

            return new ResponseEntity<>("Uspešno je izvršena rezervacija. " +
                    "Potvrda treba da Vam stigne na email adresu.", HttpStatus.OK);
        }
        catch (ClassCastException e) {
            //System.out.println("ClassCastException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceDoesNotExistException e) {
            //System.out.println("ServiceDoesNotExistException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (RequestNotValidException e) {
            //System.out.println("RequestNotValid");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DataAccessException selectException) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/reservation/boat")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> reserveBoat(@RequestBody ReservationDTO reservationRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            Boat boat = boatService.findOne(reservationRequest.getServiceId());
            if (null == boat) {
                throw new ServiceDoesNotExistException("Izabran brod ne postoji!");
            }

            reservationService.reservateBoat(reservationRequest, client, account, boat);

            return new ResponseEntity<>("Uspešno je izvršena rezervacija. " +
                    "Potvrda treba da Vam stigne na email adresu.", HttpStatus.OK);
        }
        catch (ClassCastException e) {
            //System.out.println("ClassCastException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceDoesNotExistException e) {
            //System.out.println("ServiceDoesNotExistException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (RequestNotValidException e) {
            //System.out.println("RequestNotValid");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DataAccessException selectException) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/reservation/adventure")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> reserveAdventure(@RequestBody ReservationDTO reservationRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            Adventure adventure = adventureService.findOne(reservationRequest.getServiceId());
            if (null == adventure) {
                throw new ServiceDoesNotExistException("Izabran brod ne postoji!");
            }

            reservationService.reservateAdventure(reservationRequest, client, account, adventure);

            return new ResponseEntity<>("Uspešno je izvršena rezervacija. " +
                    "Potvrda treba da Vam stigne na email adresu.", HttpStatus.OK);
        }
        catch (ClassCastException e) {
            //System.out.println("ClassCastException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceDoesNotExistException e) {
            //System.out.println("ServiceDoesNotExistException");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (RequestNotValidException e) {
            //System.out.println("RequestNotValid");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DataAccessException selectException) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }

}
