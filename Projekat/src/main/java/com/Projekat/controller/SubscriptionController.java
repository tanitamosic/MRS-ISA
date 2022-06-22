package com.Projekat.controller;

import com.Projekat.dto.ReservationDTO;
import com.Projekat.exception.*;
import com.Projekat.model.Account;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.User;
import com.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(value = "/api/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionController {

    @Autowired
    UserService userService;



    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping(value = "/add-subscription/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> addNewSubscription(@PathVariable Integer id) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            subscriptionService.addSubscription(client, id);

            return new ResponseEntity<>("Uspešno je dodata pretplata.",
                                        HttpStatus.OK);
        }
        catch (ClassCastException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (SubscritpionAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DataAccessException selectException) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/remove-subscription/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> removeSubscription(@PathVariable Integer id) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            subscriptionService.removeSubscription(client, id);

            return new ResponseEntity<>("Uspešno je uklonjena pretplata.",
                    HttpStatus.OK);
        }
        catch (ClassCastException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ServiceNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (SubscriptionDoesNotExistException e) {
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
