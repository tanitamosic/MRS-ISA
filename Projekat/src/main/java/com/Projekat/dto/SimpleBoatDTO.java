package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.Boat;

import java.util.Set;

public class SimpleBoatDTO {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private Photo primaryPhoto;
    private Set<Photo> photos;
    private Double price;

    public SimpleBoatDTO() {}

    public SimpleBoatDTO(Integer id, String name,
                         Address address, String description,
                         Photo primaryPhoto, Set<Photo> photos, Double price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.primaryPhoto = primaryPhoto;
        this.photos = photos;
        this.price = price;
    }

    public SimpleBoatDTO(Boat b) {
        this(b.getId(), b.getName(),
                b.getAddress(), b.getDescription(),
                b.getPrimaryPhoto(), b.getPhotos(), b.getPrice());
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
}
