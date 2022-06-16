package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.Adventure;

import java.time.LocalDateTime;
import java.util.Set;

public class SimpleAdventureDTO {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private Photo primaryPhoto;
    private Set<Photo> photos;
    private Double price;
    private Double rating;
    private LocalDateTime availabilityStart;
    private LocalDateTime availabilityEnd;

    public SimpleAdventureDTO() {}

    public SimpleAdventureDTO(Integer id, String name, Address address,
                              String description, Photo primaryPhoto,
                              Set<Photo> photos, Double price,
                              Double rating, LocalDateTime availabilityStart,
                              LocalDateTime availabilityEnd) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.primaryPhoto = primaryPhoto;
        this.photos = photos;
        this.price = price;
        this.rating = rating;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
    }

    public SimpleAdventureDTO(Adventure a) {
        this(a.getId(), a.getName(),
                a.getAddress(), a.getDescription(),
                a.getPrimaryPhoto(), a.getPhotos(),
                a.getPrice(), a.getRating(),
                a.getAvailabilityStart(), a.getAvailabilityEnd());
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

    public Double getRating() {
        return rating;
    }

    public LocalDateTime getAvailabilityStart() {
        return availabilityStart;
    }

    public LocalDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }
}
