package com.Projekat.controller;


import com.Projekat.dto.AdventureDTO;
import com.Projekat.dto.ComplexAdventureDTO;
import com.Projekat.dto.ComplexCottageDTO;
import com.Projekat.dto.SimpleAdventureDTO;
import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.users.Client;
import com.Projekat.model.users.Instructor;
import com.Projekat.service.*;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
public class AdventureController {

    @Autowired
    private AdventureService adventureService;
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

    @DeleteMapping(value="/instructor/delete-adventure/{usr_id}/{adv_id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> deleteAdventure(@PathVariable Integer adv_id, @PathVariable Integer usr_id) {
        try {
            Adventure a = adventureService.getAdventure(usr_id, adv_id);
            if (null == a) {
                return new ResponseEntity<>("Niste vlasnik ove avanture", HttpStatus.BAD_REQUEST);
            }
            adventureService.delete(adv_id);
            return new ResponseEntity<>("Uspešno ste obrisali avanturu", HttpStatus.OK);
        } catch (DataAccessException err) {
            return new ResponseEntity<>("Avantura ne postoji", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/instructor/update-adventure")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> updateAdventure(@RequestBody AdventureDTO ua) {
        Integer advId = ua.getAdv_id();
        Integer owner = ua.getOwner_id();
        addressService.upsertAddress(ua.getState(), ua.getCity(), ua.getStreet());
        Integer addressId = addressService.getAddressId(ua.getState(), ua.getCity(), ua.getStreet());
        adventureService.updateAdventureAddress(addressId, addressId);
        adventureService.updateAdventure(ua.getName(), ua.getAvailabilityStart(), ua.getAvailabilityEnd(), ua.getCancelTerms(),
                ua.getCapacity(), ua.getDescription(), ua.getFishingEq(), ua.getPrice(), ua.getRules(), advId);
        Adventure a = adventureService.getAdventure(owner, advId);
        Set<AdditionalService> additionalServices = new HashSet<>(Arrays.asList(ua.getAdditionalServices()));
        a.setAdditionalServices(additionalServices);
        adventureService.saveAdventure(a);
        return new ResponseEntity<>("Uspesno ste updateovali avanturu", HttpStatus.OK);
    }

    @PostMapping(value="/instructor/create-new-adventure", consumes = "application/json")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Integer> saveAdventure(@RequestBody AdventureDTO adventureDTO) {
        Instructor owner = (Instructor) userService.findUserById(adventureDTO.getOwner_id());

        addressService.upsertAddress(adventureDTO.getState(), adventureDTO.getCity(), adventureDTO.getStreet());

        Address address = addressService.getAddress(adventureDTO.getState(), adventureDTO.getCity(), adventureDTO.getStreet());

        Set<AdditionalService> additionalServices = new HashSet<>(Arrays.asList(adventureDTO.getAdditionalServices()));

        additionalServicesService.addServices(additionalServices);

        Adventure a = new Adventure(adventureDTO, address, owner, additionalServices);
        a = adventureService.saveAdventure(a);
        return new ResponseEntity<>(a.getId(), HttpStatus.OK);
    }

    @GetMapping(value = "/adventures/all")
    public ResponseEntity<List<SimpleAdventureDTO>> getAllAdventures(){
        List<Adventure> adventures = adventureService.findAll();
        List<SimpleAdventureDTO> adventureDTO = new ArrayList<>();
        for (Adventure a : adventures){
            adventureDTO.add(new SimpleAdventureDTO(a));
        }
        return new ResponseEntity<>(adventureDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/adventures/all/withPagination")
    public Page<SimpleAdventureDTO> getAdventuresWithPagination(Pageable page) {
        Page<Adventure> pageAdventure = adventureService.findAll(page);
        pageAdventure.forEach(new Consumer<Adventure>() {
            @Override
            public void accept(Adventure adventure) {
                if (adventure.getDeleted()) {

                }
            }
        });
        Page<SimpleAdventureDTO> pageAdventureDTO = pageAdventure.map(this::convertToSimpleAdventureDTO);
        return pageAdventureDTO;
    }
    private SimpleAdventureDTO convertToSimpleAdventureDTO(Adventure a) {
        return new SimpleAdventureDTO(a);
    }


    @GetMapping(value = "/adventures/getAdventure/{id}")
    public ResponseEntity<ComplexAdventureDTO> getComplexAdventure(@PathVariable Integer id) {

        Adventure adventure = adventureService.findOne(id);

        // cottage must exist
        if (adventure == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexAdventureDTO(adventure), HttpStatus.OK);
    }

    @DeleteMapping(value="/admin/delete-adventure/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAdventure(@PathVariable Integer id) {
        try {
            adventureService.delete(id);
            return new ResponseEntity<>("Uspešno ste obrisali avanturu", HttpStatus.OK);
        } catch (DataAccessException err) {
            return new ResponseEntity<>("Avantura ne postoji", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/instructor/post-adventure-image", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> createAdventure(@RequestParam("file") MultipartFile multipartFile,
                                                  @RequestParam("adventureId") Integer id) {
        try {
            String fileName = generateRandomFileName();

            String folder = "src/frontend/public/img/adventures/";
            byte[] file = multipartFile.getBytes();
            Path path = Paths.get(folder + fileName);
            Files.createFile(path);
            Files.write(path, file);

            String filepathForDB = "img/adventures/";
            Photo newPhoto = new Photo();
            newPhoto.setAssetPath(filepathForDB + fileName);
            photoService.saveNewPhoto(newPhoto.getAssetPath(), id);
            Integer photoId = photoService.getPhotoId(newPhoto.getAssetPath());
            Adventure a = adventureService.findOne(id);

            if (null == a.getPrimaryPhoto()) {
                adventureService.setAdventurePrimaryPhoto(id, photoId);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Greska pri ubacivanju slika", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Slike uspesno dodate", HttpStatus.OK);
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

    @GetMapping(value="/instructor/{usr_id}/adventures/all")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Adventure>> getAllInstructorsAdventures(@PathVariable Integer usr_id) {
        List<Adventure> allAdventures = adventureService.getAllInstructorsAdventures(usr_id);
        return new ResponseEntity<>(allAdventures, HttpStatus.OK);
    }

    @GetMapping(value="/instructor/{usr_id}/adventures/getadventure/{adv_id}")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Adventure> getAllInstructorsAdventures(@PathVariable Integer usr_id, @PathVariable Integer adv_id) {
        Adventure adventure = adventureService.getAdventure(usr_id, adv_id);
        if (null == adventure) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(adventure, HttpStatus.OK);
    }

    @GetMapping("/instructor/{adv_id}/find-client")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Client> isAdventureReserved(@PathVariable Integer adv_id) {
        Client c = reservationService.findIfAdventureIsReserved(adv_id);
        // returning null as client is fine. cuz front knows to handle null for return value
        return new ResponseEntity<>(c, HttpStatus.OK);

    }
}
