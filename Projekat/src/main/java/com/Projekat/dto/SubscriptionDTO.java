package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.services.Subscription;

import java.util.Set;

public class SubscriptionDTO {

    private Integer serviceId;
    private Integer subscriptionId;
    private String name;
    private Address address;
    private String description;
    private Photo primaryPhoto;
    private Set<Photo> photos;
    private Double price;
    private Double rating;

    public SubscriptionDTO() {}

    public SubscriptionDTO(Integer serviceId, Integer subscriptionId,
                           String name, Address address, String description,
                           Photo primaryPhoto, Set<Photo> photos, Double price, Double rating) {
        this.serviceId = serviceId;
        this.subscriptionId = subscriptionId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.primaryPhoto = primaryPhoto;
        this.photos = photos;
        this.price = price;
        this.rating = rating;
    }

    public SubscriptionDTO(Subscription s) {
        this(s.getService().getId(), s.getId(),
                s.getService().getName(), s.getService().getAddress(), s.getService().getDescription(),
                s.getService().getPrimaryPhoto(), s.getService().getPhotos(),
                s.getService().getPrice(), s.getService().getRating());
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
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
}
