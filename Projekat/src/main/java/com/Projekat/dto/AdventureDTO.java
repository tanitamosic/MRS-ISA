package com.Projekat.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class AdventureDTO {

    private Integer owner_id;

//    private String dateFrom;
//    private String timeFrom;
//    private String dateTo;
//    private String timeTo;
//    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm")
    private LocalDateTime availabilityStart;
//    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm")
    private LocalDateTime availabilityEnd;

    private String fishingEq;
    private String cancelTerms;
    private Integer capacity;
    private String rules;
    private Double price;
    private String name;
    private String description;

    private String[] additionalServices;

    private String state;
    private String city;
    private String street;

    public AdventureDTO() {
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
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

    public String getFishingEq() {
        return fishingEq;
    }

    public void setFishingEq(String fishingEq) {
        this.fishingEq = fishingEq;
    }

    public String getCancelTerms() {
        return cancelTerms;
    }

    public void setCancelTerms(String cancelTerms) {
        this.cancelTerms = cancelTerms;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String[] additionalServices) {
        this.additionalServices = additionalServices;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
