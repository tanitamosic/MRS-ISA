package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Boat;

import javax.persistence.Column;
import java.util.Set;

public class ComplexBoatDTO {

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

    private String type;
    private Double length;
    private Integer numberOfEngines;
    private Double enginePower;
    private Double maxSpeed;
    private String navigationalEquipment;
    private Integer capacity;
    private String fishingEquipment;

    public ComplexBoatDTO() {}

    public ComplexBoatDTO(Integer id, String name, Address address,
                          String description, Photo primaryPhoto,
                          Set<Photo> photos, Double price,
                          String rules, String cancellationTerms,
                          Set<AdditionalService> additionalServices,
                          String type, Double length,
                          Integer numberOfEngines, Double enginePower,
                          Double maxSpeed, String navigationalEquipment,
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
        this.type = type;
        this.length = length;
        this.numberOfEngines = numberOfEngines;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.navigationalEquipment = navigationalEquipment;
        this.capacity = capacity;
        this.fishingEquipment = fishingEquipment;
    }

    public ComplexBoatDTO(Boat b) {
        this(b.getId(), b.getName(), b.getAddress(), b.getDescription(),
                b.getPrimaryPhoto(), b.getPhotos(), b.getPrice(), b.getRules(),
                b.getCancellationTerms(), b.getAdditionalServices(), b.getType(), b.getLength(), b.getNumberOfEngines(),
                b.getEnginePower(), b.getMaxSpeed(), b.getNavigationalEquipment(), b.getCapacity(), b.getFishingEquipment());
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

    public String getType() {
        return type;
    }

    public Double getLength() {
        return length;
    }

    public Integer getNumberOfEngines() {
        return numberOfEngines;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public String getNavigationalEquipment() {
        return navigationalEquipment;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }
}
