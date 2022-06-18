package com.Projekat.controller;

import com.Projekat.dto.ReservationDTO;
import com.Projekat.exception.RequestNotValidException;
import com.Projekat.exception.ServiceDoesNotExistException;
import com.Projekat.exception.ServiceNotAvailableException;
import com.Projekat.model.Account;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import com.Projekat.service.BoatService;
import com.Projekat.service.CottageService;
import com.Projekat.service.ReservationService;
import com.Projekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {

    @Autowired
    UserService userService;

    @Autowired
    CottageService cottageService;

    @Autowired
    BoatService boatService;

    @Autowired
    ReservationService reservationService;


    @PostMapping(value = "/cottage")
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


    @PostMapping(value = "/boat")
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


}
