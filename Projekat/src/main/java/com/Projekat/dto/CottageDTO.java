package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.services.Cottage;

import java.time.LocalDateTime;

public class CottageDTO {
    private Integer id;
    private String name;
    private Address address;
    private String description;
    private String rules;
    private Double price;
    private String cancellationTerms;
    private LocalDateTime availabilityStart;
    private LocalDateTime availabilityEnd;
    private Boolean isDeleted;
    private Integer numberOfRooms;
    private Integer numberOfBeds;
    private Integer owner_id;
    private String[] additionalServices;

    private String state;
    private String city;
    private String street;


    public CottageDTO(Cottage cottage){
        this(   cottage.getId(),                cottage.getName(),              cottage.getOwner().getId(),
                cottage.getDescription(),       cottage.getRules(),             cottage.getPrice(),
                cottage.getCancellationTerms(),  cottage.getAvailabilityStart(), cottage.getAvailabilityEnd(),
                cottage.getDeleted(),           cottage.getNumberOfRooms(),     cottage.getNumberOfBeds());
    }

    public CottageDTO(Integer id, String name, Integer ownerId, String description,
                      String rules, Double price, String cancellationTerms,
                      LocalDateTime availabilityStart, LocalDateTime availabilityEnd,
                      Boolean isDeleted, Integer numberOfRooms, Integer numberOfBeds) {
        this.id = id;
        this.name = name;
//        this.address = address;
        this.description = description;
        this.rules = rules;
        this.price = price;
        this.cancellationTerms = cancellationTerms;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
        this.isDeleted = isDeleted;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBeds = numberOfBeds;
        this.owner_id = ownerId;
    }

    public CottageDTO(){
    }
    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
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

    public LocalDateTime getAvailabilityStart() {
        return availabilityStart;
    }

    public LocalDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
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
