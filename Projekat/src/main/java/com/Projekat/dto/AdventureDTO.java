package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.services.Adventure;

import java.time.LocalDateTime;

public class AdventureDTO {

    private final String name;
    private Address address;
    private final String description;
    private final String rules;
    private final Double price;
    private final String cancellationTerms;
    private final LocalDateTime availabilityStart;
    private final LocalDateTime availabilityEnd;
    private final Boolean isDeleted;
    private final Integer capacity;
    private final String fishingEquipment;

    public AdventureDTO(Adventure ad) {
        this(ad.getName(), /*ad.getAddress(),*/ ad.getDescription(), ad.getRules(), ad.getPrice(),
                ad.getCancellationTerms(), ad.getAvailabilityStart(), ad.getAvailabilityEnd(), ad.getDeleted(),
                ad.getCapacity(), ad.getFishingEquipment());
    }

    public AdventureDTO(String name, /*Address address,*/ String description, String rules, Double price,
                        String cancellationTerms, LocalDateTime availabilityStart, LocalDateTime availabilityEnd,
                        Boolean isDeleted, Integer capacity, String fishingEquipment) {
        this.name = name;
        //this.address = address;
        this.description = description;
        this.rules = rules;
        this.price = price;
        this.cancellationTerms = cancellationTerms;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
        this.isDeleted = isDeleted;
        this.capacity = capacity;
        this.fishingEquipment = fishingEquipment;
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

    public LocalDateTime getAvailabilityStart() {
        return availabilityStart;
    }

    public LocalDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }
}
