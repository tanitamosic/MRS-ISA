package com.Projekat.controller;

import com.Projekat.dto.AdventureDTO;
import com.Projekat.dto.ComplexCottageDTO;
import com.Projekat.dto.CottageDTO;
import com.Projekat.dto.SimpleCottageDTO;
import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.users.CottageOwner;
import com.Projekat.model.users.Instructor;
import com.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping(value = "api")
public class CottageController {

    @Autowired
    private CottageService cottageService;
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

    @GetMapping(value = "/cottages/all")
    public ResponseEntity<List<CottageDTO>> getAllCottages(){
        List<Cottage> cottages = cottageService.findAll();
        List<CottageDTO> cottagesDTO = new ArrayList<>();
        for (Cottage c : cottages){
            cottagesDTO.add(new CottageDTO(c));
        }
        return new ResponseEntity<>(cottagesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/cottages/all/withPagination")
    public Page<SimpleCottageDTO> getCottagesWithPagination(Pageable page) {
        Page<Cottage> pageCottage = cottageService.findAll(page);
        Page<SimpleCottageDTO> pageCottageDTO = pageCottage.map(this::convertToSimpleCottageDTO);
        return pageCottageDTO;
    }

    @GetMapping(value = "/cottages/{owner}/withPagination")
    public Page<SimpleCottageDTO> getOwnedCottagesWithPagination(Pageable page, @PathVariable String owner) {
        Integer ownerId = accountService.findByUsername(owner).getId();
        Page<Cottage> pageCottage = cottageService.findAllByOwner(page,ownerId);
        Page<SimpleCottageDTO> pageCottageDTO = pageCottage.map(this::convertToSimpleCottageDTO);
        return pageCottageDTO;
    }

    @PostMapping(value="/co/add-cottage", consumes = "application/json")
    @PreAuthorize("hasRole('COTTAGEOWNER')")
    public ResponseEntity<Integer> saveCottage(@RequestBody CottageDTO cottageDTO) {
        CottageOwner owner = (CottageOwner) userService.findUserById(cottageDTO.getOwner_id());
        addressService.upsertAddress(cottageDTO.getState(), cottageDTO.getCity(), cottageDTO.getStreet());
        Address address = addressService.getAddress(cottageDTO.getState(), cottageDTO.getCity(), cottageDTO.getStreet());
        Set<AdditionalService> additionalServices = getAdditionalServices(cottageDTO);
        additionalServicesService.addServices(additionalServices);
        Cottage a = new Cottage(cottageDTO, address, owner, additionalServices);
        a = cottageService.save(a);
        return new ResponseEntity<>(a.getId(), HttpStatus.OK);
    }

    @PostMapping(value="/co/update-cottage")
    @PreAuthorize("hasRole('COTTAGEOWNER')")
    public ResponseEntity<String> updateCottage(@RequestBody CottageDTO ua) {
        CottageOwner owner = (CottageOwner) userService.findUserById(ua.getOwner_id());
        addressService.upsertAddress(ua.getState(), ua.getCity(), ua.getStreet());
        Address address = addressService.getAddress(ua.getState(), ua.getCity(), ua.getStreet());
        Set<AdditionalService> additionalServices = getAdditionalServices(ua);
        additionalServicesService.addServices(additionalServices);
        Cottage a = new Cottage(ua, address, owner, additionalServices);
        cottageService.save(a);
        return new ResponseEntity<>("Uspesno ste updateovali avanturu", HttpStatus.OK);
    }

    private Set<AdditionalService> getAdditionalServices(CottageDTO cottageDTO) {
        Set<AdditionalService> additionalServices = new HashSet<>(cottageDTO.getAdditionalServices().length);
        for (String service: cottageDTO.getAdditionalServices()) {
            AdditionalService addedService = new AdditionalService();
            String name = service.split(" - \\$")[0];
            Double price = Double.parseDouble(service.split(" - \\$")[1]);
            addedService.setName(name);
            addedService.setPrice(price);
            additionalServices.add(addedService);
        }
        return additionalServices;
    }

    @PostMapping(value="/co/post-cottage-image", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('COTTAGEOWNER')")
    public ResponseEntity<String> createCottage(@RequestParam("file") MultipartFile multipartFile,
                                                  @RequestParam("cottageId") Integer id) {
        try {
            String fileName = generateRandomFileName();

            String folder = "src/frontend/public/img/cottages/";
            byte[] file = multipartFile.getBytes();
            Path path = Paths.get(folder + fileName);
            Files.createFile(path);
            Files.write(path, file);

            String filepathForDB = "img/cottages/";
            Photo newPhoto = new Photo();
            newPhoto.setAssetPath(filepathForDB + fileName);
            photoService.saveNewPhoto(newPhoto.getAssetPath(), id);
            Integer photoId = photoService.getPhotoId(newPhoto.getAssetPath());
            Cottage a = cottageService.findOne(id);

            if (null == a.getPrimaryPhoto()) {
                cottageService.setCottagePrimaryPhoto(id, photoId);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Greska pri ubacivanju slika", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Slike uspesno dodate", HttpStatus.OK);
    }

    private SimpleCottageDTO convertToSimpleCottageDTO(Cottage c) {
        return new SimpleCottageDTO(c);
    }

    /*
    private CottageDTO convertToCottageDTO(Cottage cottage) {
        CottageDTO dto = new CottageDTO(cottage);
        return dto;
    }
    */


    @GetMapping(value = "/cottages/{id}")
    public ResponseEntity<CottageDTO> getCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CottageDTO(cottage), HttpStatus.OK);
    }

    @GetMapping(value = "/cottages/getCottage/{id}")
    public ResponseEntity<ComplexCottageDTO> getComplexCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        // cottage must exist
        if (cottage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ComplexCottageDTO(cottage), HttpStatus.OK);
    }


    @DeleteMapping(value = "/admin/delete-cottage/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCottage(@PathVariable Integer id) {

        Cottage cottage = cottageService.findOne(id);

        if (cottage != null) {
            cottageService.remove(id);
            return new ResponseEntity<>("Uspešno ste obrisali vikendicu.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vikendica ne postoji", HttpStatus.NOT_FOUND);
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

}

