package com.Projekat.controller;

import com.Projekat.dto.CottageSearchDTO;
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

//    @GetMapping(value = "/cottages")
//    @PostMapping(value = "/cottages/{page}/{size}")
    @PostMapping(value = "/cottages")
    public ResponseEntity<Page<Cottage>> searchCottages(Pageable page, @RequestBody CottageSearchDTO cottageSearchDTO) {
//        Pageable pag = PageRequest.of(page, size);
        return new ResponseEntity<>(searchService.getCottages(page, cottageSearchDTO),
                HttpStatus.OK);
    }

//    @PostMapping(consumes = "application/json", value = "/cottages/proba")
//    public ResponseEntity<CottageSearchDTO> searchCottagesProba(@RequestBody CottageSearchDTO cottageSearchDTO) {
//        return new ResponseEntity<>(new CottageSearchDTO(),
//                HttpStatus.OK);
//    }


}
