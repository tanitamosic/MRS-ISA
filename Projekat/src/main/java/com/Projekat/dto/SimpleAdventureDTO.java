package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.Adventure;

import java.util.Set;

public class SimpleAdventureDTO {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private Photo primaryPhoto;
    private Set<Photo> photos;
    private Double price;

    public SimpleAdventureDTO() {}

    public SimpleAdventureDTO(Integer id, String name, Address address, String description, Photo primaryPhoto, Set<Photo> photos, Double price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.primaryPhoto = primaryPhoto;
        this.photos = photos;
        this.price = price;
    }

    public SimpleAdventureDTO(Adventure a) {
        this(a.getId(), a.getName(),
                a.getAddress(), a.getDescription(),
                a.getPrimaryPhoto(), a.getPhotos(), a.getPrice());
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
