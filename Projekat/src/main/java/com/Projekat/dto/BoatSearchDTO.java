package com.Projekat.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class BoatSearchDTO {

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

    private String boatName;
    private String boatType;
    private Integer capacity;
    private String navigationalEquipment;
    private String fishingEquipment;

    public BoatSearchDTO() {}

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

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getNavigationalEquipment() {
        return navigationalEquipment;
    }

    public void setNavigationalEquipment(String navigationalEquipment) {
        this.navigationalEquipment = navigationalEquipment;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
