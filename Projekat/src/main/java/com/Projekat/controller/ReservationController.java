package com.Projekat.controller;


import com.Projekat.dto.CutDTO;
import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.services.Service;
import com.Projekat.model.users.Instructor;
import com.Projekat.dto.*;
import com.Projekat.exception.*;
import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping(value = "/instructor/create-client-reservation")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> reserveClientAdventure(@RequestBody ReservationDTO reservationRequest) {
        try { Instructor inst = (Instructor) user;      //ClassCastException e

        Adventure adventure = adventureService.findOne(reservationRequest.getServiceId());
        if (null == adventure) {
            throw new ServiceDoesNotExistException("Izabrana vikendica ne postoji!");
        }
        Client c = (Client) userService.findUserById(reservationRequest.getClientId());
        reservationService.reserveAdventureForClient(reservationRequest, account, c, adventure);

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
}
    @PostMapping(value = "/client/reservations")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Page<ReservationSimpleDTO>> getAllUserReservations(Pageable page,
                                                                             @RequestBody SortParametersReservationsDTO parameters) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Pageable page2 = null;
            if(parameters.getSortByDateAscending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("start_date").ascending());
            }
            else if (parameters.getSortByDateDescending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("start_date").descending());
            }
            else if (parameters.getSortByPriceAscending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("price").ascending());
            }
            else if (parameters.getSortByPrisceDescending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("price").descending());
            }
            else {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("start_date").ascending());
            }

            Client client = (Client) user;      //ClassCastException e
            Page<Reservation> queryPage = reservationService.getAllActiveUserReservations(user.getId(), page2);
            Page<ReservationSimpleDTO> returnPage = queryPage.map(this::convertToReservationSimpleDTO);
            return new ResponseEntity<>(returnPage, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
    private ReservationSimpleDTO convertToReservationSimpleDTO(Reservation r) {
        return new ReservationSimpleDTO(r);
    }

    @PostMapping(value = "/client/historicalreservations")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Page<ReservationSimpleDTO>> getAllHistoricalUserReservations(Pageable page,
                                                                             @RequestBody SortParametersReservationsDTO parameters) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Pageable page2 = null;
            if(parameters.getSortByDateAscending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("start_date").ascending());
            }
            else if (parameters.getSortByDateDescending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("start_date").descending());
            }
            else if (parameters.getSortByPriceAscending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("price").ascending());
            }
            else if (parameters.getSortByPrisceDescending() == true) {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("price").descending());
            }
            else {
                page2 = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("start_date").ascending());
            }

            Client client = (Client) user;      //ClassCastException e
            Page<Reservation> queryPage = reservationService.getAllHistoricalUserReservations(user.getId(), page2);
            Page<ReservationSimpleDTO> returnPage = queryPage.map(this::convertToReservationSimpleDTO);
            return new ResponseEntity<>(returnPage, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/client/cancelreservation/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> cancelReservation(@PathVariable Integer id) {
        // id je id rezervacije koju želimo da otkažemo
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            reservationService.cancelReservation(client, id);

            return new ResponseEntity<>("Rezervacija je uspešno otkazana.", HttpStatus.OK);
        }
        catch (ClassCastException e) {
            //System.out.println("ClassCastException");
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (ReservationDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReservationOwnerNotAppropriateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReservationStatusNotAppropriateForCancelationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DeadlineForReservationCancellationPassedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/client/makecomplaint")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> makeComplaint(@RequestBody CLientComplaintDTO complaintDTO) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            if (complaintDTO.getComplaintText() == null || complaintDTO.getComplaintText().equals("")) {
                throw new ComplaintTextEmptyException("Morate napisati tekst žalbe!");
            }
            reservationService.makeComplaint(client, complaintDTO.getReservationId(), complaintDTO.getComplaintText());

            return new ResponseEntity<>("Žalba je uspešno priložena.", HttpStatus.OK);
        }
        catch (ClassCastException e) {
            //System.out.println("ClassCastException");
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (ReservationDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReservationOwnerNotAppropriateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReservationStatusNotAppropriateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ComplaintAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ComplaintTextEmptyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/client/reviewreservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> reviewReservation(@RequestBody ClientReservationReviewDTO crrDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = (Account) auth.getPrincipal();
            String username = account.getUsername();
            User user = userService.getUserData(username);

            Client client = (Client) user;      //ClassCastException e

            if (crrDto.getRating() == null) {
                throw new ReviewRatingEmptyException("Morate uneti ocenu!");
            }
            if (crrDto.getComment() == null || crrDto.getComment().equals("")) {
                throw new ReviewCommentEmptyException("Morate napisati komentar!");
            }
            reservationService.reviewReservation(client, crrDto);

            return new ResponseEntity<>("Ocena je uspešno poslata.", HttpStatus.OK);
        }
        catch (ClassCastException e) {
            //System.out.println("ClassCastException");
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
        catch (ReservationDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReservationOwnerNotAppropriateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReservationStatusNotAppropriateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReviewAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReviewRatingEmptyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ReviewCommentEmptyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Došlo je do greške!", HttpStatus.BAD_REQUEST);
        }
    }


}
