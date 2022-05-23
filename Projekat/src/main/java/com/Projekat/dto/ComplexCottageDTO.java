package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Cottage;

import java.time.LocalDateTime;
import java.util.Set;

public class ComplexCottageDTO {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private String rules;
    private Double price;
    private String cancellationTerms;
    private Integer numberOfRooms;
    private Integer numberOfBeds;
    private Set<AdditionalService> additionalServices;
    private Photo primaryPhoto;

    public ComplexCottageDTO() {}

    public ComplexCottageDTO(Integer id, String name,
                             Address address, String description,
                             String rules, Double price,
                             String cancellationTerms,
                             Integer numberOfRooms, Integer numberOfBeds,
                             Set<AdditionalService> additionalServices,Photo primaryPhoto) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.rules = rules;
        this.price = price;
        this.cancellationTerms = cancellationTerms;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBeds = numberOfBeds;
        this.additionalServices = additionalServices;
        this.primaryPhoto = primaryPhoto;
    }

    public ComplexCottageDTO(Cottage c){
        this(c.getId(), c.getName(), c.getAddress(),
                c.getDescription(), c.getRules(),
                c.getPrice(), c.getCancellationTerms(),
                c.getNumberOfRooms(), c.getNumberOfBeds(), c.getAdditionalServices(),c.getPrimaryPhoto());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getRules() {
        return rules;
    }

    public Double getPrice() {
        return price;
    }

    public String getCancellationTerms() {
        return cancellationTerms;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public Photo getPrimaryPhoto() {
        return primaryPhoto;
    }
}
