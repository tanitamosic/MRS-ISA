package com.Projekat.controller;

import com.Projekat.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CottageOwnerController {

    @Autowired
    CottageService cottageService;



}
