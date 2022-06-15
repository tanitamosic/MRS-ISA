package com.Projekat.controller;

import com.Projekat.dto.*;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import com.Projekat.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @PostMapping(value = "/cottages")
    public ResponseEntity<Page<SimpleCottageDTO>> searchCottages(Pageable page, @RequestBody CottageSearchDTO cottageSearchDTO) {
        Page<Cottage> cottages = searchService.getCottages(page, cottageSearchDTO);
        Page<SimpleCottageDTO> pageCottageDTO = cottages.map(this::convertToSimpleCottageDTO);
        return new ResponseEntity<>(pageCottageDTO, HttpStatus.OK);
    }
    private SimpleCottageDTO convertToSimpleCottageDTO(Cottage c) {
        return new SimpleCottageDTO(c);
    }

    @PostMapping(value = "/adventures")
    public ResponseEntity<Page<SimpleAdventureDTO>> searchAdventures(Pageable page, @RequestBody AdventureSearchDTO adventureSearchDTO) {
        Page<Adventure> adventures = searchService.getAdventures(page, adventureSearchDTO);
        Page<SimpleAdventureDTO> pageAdventureDTO = adventures.map(this::convertToSimpleAdventureDTO);
        return new ResponseEntity<>(pageAdventureDTO, HttpStatus.OK);
    }
    private SimpleAdventureDTO convertToSimpleAdventureDTO(Adventure a) {
        return new SimpleAdventureDTO(a);
    }

    @PostMapping(value = "/boats")
    public ResponseEntity<Page<SimpleBoatDTO>> searchBoats(Pageable page, @RequestBody BoatSearchDTO boatSearchDTO) {
        Page<Boat> boats = searchService.getBoats(page, boatSearchDTO);
        Page<SimpleBoatDTO> pageBoatDTO = boats.map(this::convertToSimpleBoatDTO);
        return new ResponseEntity<>(pageBoatDTO, HttpStatus.OK);
    }
    private SimpleBoatDTO convertToSimpleBoatDTO(Boat b) {
        return new SimpleBoatDTO(b);
    }

}
