package com.Projekat.controller;

import com.Projekat.dto.*;
import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Boat;
import com.Projekat.model.users.BoatOwner;
import com.Projekat.model.users.Client;
import com.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class BoatController {

    @Autowired
    private BoatService boatService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdditionalServicesService additionalServicesService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "/boats/getBoat/{id}")
    public ResponseEntity<ComplexBoatDTO> getComplexBoat(@PathVariable Integer id) {

        Boat boat = boatService.findOne(id);

        // boat must exist
        if (boat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexBoatDTO(boat), HttpStatus.OK);
    }

    @GetMapping(value = "/boats/all")
    public ResponseEntity<List<SimpleBoatDTO>> getAllBoats(){
        List<Boat> boats = boatService.findAll();
        List<SimpleBoatDTO> boatsDTO = new ArrayList<>();
        for (Boat b : boats){
            boatsDTO.add(new SimpleBoatDTO(b));
        }
        return new ResponseEntity<>(boatsDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/boats/{owner}/withPagination")
    public Page<SimpleBoatDTO> getOwnedBoatsWithPagination(Pageable page, @PathVariable String owner) {
        Integer ownerId = accountService.findByUsername(owner).getId();
        Page<Boat> pageBoat = boatService.findAllByOwner(page,ownerId);
        Page<SimpleBoatDTO> pageBoatDTO = pageBoat.map(this::convertToSimpleBoatDTO);
        return pageBoatDTO;
    }

    @PostMapping(value="/bo/add-boat", consumes = "application/json")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Integer> saveBoat(@RequestBody BoatDTO boatDTO) {
        BoatOwner owner = (BoatOwner) userService.findUserById(boatDTO.getOwner_id());
        addressService.upsertAddress(boatDTO.getState(), boatDTO.getCity(), boatDTO.getStreet());
        Address address = addressService.getAddress(boatDTO.getState(), boatDTO.getCity(), boatDTO.getStreet());
        Set<AdditionalService> additionalServices = getAdditionalServices(boatDTO);
        additionalServicesService.addServices(additionalServices);
        Boat a = new Boat(boatDTO, address, owner, additionalServices);
        a = boatService.saveBoat(a);
        return new ResponseEntity<>(a.getId(), HttpStatus.OK);
    }

    @PostMapping(value="/bo/update-boat")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> updateBoat(@RequestBody BoatDTO ua) {
        BoatOwner owner = (BoatOwner) userService.findUserById(ua.getOwner_id());
        addressService.upsertAddress(ua.getState(), ua.getCity(), ua.getStreet());
        Address address = addressService.getAddress(ua.getState(), ua.getCity(), ua.getStreet());
        Set<AdditionalService> additionalServices = getAdditionalServices(ua);
        additionalServicesService.addServices(additionalServices);
        Boat a = new Boat(ua, address, owner, additionalServices);
        boatService.save(a);
        return new ResponseEntity<>("Uspesno ste updateovali avanturu", HttpStatus.OK);
    }

    @DeleteMapping(value="/bo/delete-boat/{usr_id}/{adv_id}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> deleteBoat(@PathVariable Integer adv_id, @PathVariable Integer usr_id) {
        try {
            Boat a = boatService.getBoat(usr_id, adv_id);
            if (null == a) {
                return new ResponseEntity<>("Niste vlasnik ove avanture", HttpStatus.BAD_REQUEST);
            }
            boatService.delete(adv_id);
            return new ResponseEntity<>("Uspešno ste obrisali avanturu", HttpStatus.OK);
        } catch (DataAccessException err) {
            return new ResponseEntity<>("Avantura ne postoji", HttpStatus.NOT_FOUND);
        }
    }

    private Set<AdditionalService> getAdditionalServices(BoatDTO boatDTO) {
        Set<AdditionalService> additionalServices = new HashSet<>(boatDTO.getAdditionalServices().length);
        for (String service: boatDTO.getAdditionalServices()) {
            AdditionalService addedService = new AdditionalService();
            String name = service.split(" - \\$")[0];
            Double price = Double.parseDouble(service.split(" - \\$")[1]);
            addedService.setName(name);
            addedService.setPrice(price);
            additionalServices.add(addedService);
        }
        return additionalServices;
    }

    @PostMapping(value="/bo/post-boat-image", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> createBoat(@RequestParam("file") MultipartFile multipartFile,
                                                @RequestParam("boatId") Integer id) {
        try {
            String fileName = generateRandomFileName();

            String folder = "src/frontend/public/img/boats/";
            byte[] file = multipartFile.getBytes();
            Path path = Paths.get(folder + fileName);
            Files.createFile(path);
            Files.write(path, file);

            String filepathForDB = "img/boats/";
            Photo newPhoto = new Photo();
            newPhoto.setAssetPath(filepathForDB + fileName);
            photoService.saveNewPhoto(newPhoto.getAssetPath(), id);
            Integer photoId = photoService.getPhotoId(newPhoto.getAssetPath());
            Boat a = boatService.findOne(id);

            if (null == a.getPrimaryPhoto()) {
                boatService.setBoatPrimaryPhoto(id, photoId);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Greska pri ubacivanju slika", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Slike uspesno dodate", HttpStatus.OK);
    }


    @GetMapping(value = "/boats/all/withPagination")
    public Page<SimpleBoatDTO> getCottagesWithPagination(Pageable page) {
        Page<Boat> pageBoat = boatService.findAll(page);
        Page<SimpleBoatDTO> pageBoatDTO = pageBoat.map(this::convertToSimpleBoatDTO);
        return pageBoatDTO;
    }
    private SimpleBoatDTO convertToSimpleBoatDTO(Boat b) {
        return new SimpleBoatDTO(b);
    }

    @DeleteMapping(value="/admin/delete-boat/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAdventure(@PathVariable Integer id) {
        try {
            boatService.delete(id);
            return new ResponseEntity<>("Uspešno ste obrisali brod", HttpStatus.OK);
        } catch (DataAccessException err) {
            return new ResponseEntity<>("Brod ne postoji", HttpStatus.NOT_FOUND);
        }
    }

    private String generateRandomFileName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 18;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString() + ".jpg";
    }

    @GetMapping("/bo/{boat_id}/find-client")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Client> isBoatReserved(@PathVariable Integer boat_id) {
        Client c = (Client) reservationService.findIfAdventureIsReserved(boat_id);
        // returning null as client is fine. cuz front knows to handle null for return value
        return new ResponseEntity<>(c, HttpStatus.OK);

    }

}
