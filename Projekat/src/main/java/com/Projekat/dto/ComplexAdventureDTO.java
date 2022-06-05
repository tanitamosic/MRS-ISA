package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Adventure;

import javax.persistence.Column;
import java.util.Set;

public class ComplexAdventureDTO {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private Photo primaryPhoto;
    private Set<Photo> photos;
    private Double price;

    private String rules;
    private String cancellationTerms;
    private Set<AdditionalService> additionalServices;

    private Integer capacity;
    private String fishingEquipment;

    public ComplexAdventureDTO() {}

    public ComplexAdventureDTO(Integer id, String name,
                               Address address, String description,
                               Photo primaryPhoto, Set<Photo> photos,
                               Double price, String rules, String cancellationTerms,
                               Set<AdditionalService> additionalServices,
                               Integer capacity, String fishingEquipment) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.primaryPhoto = primaryPhoto;
        this.photos = photos;
        this.price = price;
        this.rules = rules;
        this.cancellationTerms = cancellationTerms;
        this.additionalServices = additionalServices;
        this.capacity = capacity;
        this.fishingEquipment = fishingEquipment;
    }

    public ComplexAdventureDTO(Adventure a){
        this(a.getId(), a.getName(), a.getAddress(),
                a.getDescription(), a.getPrimaryPhoto(),
                a.getPhotos(), a.getPrice(), a.getRules(),
                a.getCancellationTerms(), a.getAdditionalServices(),
                a.getCapacity(), a.getFishingEquipment());
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

    public Photo getPrimaryPhoto() {
        return primaryPhoto;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Double getPrice() {
        return price;
    }

    public String getRules() {
        return rules;
    }

    public String getCancellationTerms() {
        return cancellationTerms;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }
}
