package com.Projekat.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class AdventureSearchDTO {

    private String generalSearchField;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime availabilityStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime availabilityEnd;
    private String state;
    private String city;
    private Double priceFrom;
    private Double priceTo;
    private Double rating;

    private String adventureName;
    private Integer numberOfPersons;
    private String fishingEquipment;


    public AdventureSearchDTO() {}

    public AdventureSearchDTO(String generalSearchField,
                              LocalDateTime availabilityStart,
                              LocalDateTime availabilityEnd,
                              String state, String city,
                              Double priceFrom, Double priceTo,
                              Double rating, String adventureName,
                              Integer numberOfPersons, String fishingEquipment) {
        this.generalSearchField = generalSearchField;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
        this.state = state;
        this.city = city;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.rating = rating;
        this.adventureName = adventureName;
        this.numberOfPersons = numberOfPersons;
        this.fishingEquipment = fishingEquipment;
    }

    public String getGeneralSearchField() {
        return generalSearchField;
    }

    public void setGeneralSearchField(String generalSearchField) {
        this.generalSearchField = generalSearchField;
    }

    public LocalDateTime getAvailabilityStart() {
        return availabilityStart;
    }

    public void setAvailabilityStart(LocalDateTime availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public LocalDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(LocalDateTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
