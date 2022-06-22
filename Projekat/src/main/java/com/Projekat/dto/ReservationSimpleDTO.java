package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.reservations.ReservationStatus;
import com.Projekat.model.reservations.submitions.Complaint;
import com.Projekat.model.reservations.submitions.Review;
import com.Projekat.model.services.Service;
import com.Projekat.model.users.Client;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationSimpleDTO {

    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer capacity;
    private Double price;
    private ReservationStatus status;
    private Review review;
    //private Complaint complaint;

    private String serviceName;
    private Address address;
    private Set<Photo> photos;
    private String cancelationTerms;
    private Double rating;

    public ReservationSimpleDTO() {}

    public ReservationSimpleDTO(Integer id, LocalDateTime startDate,
                                LocalDateTime endDate, Integer capacity,
                                Double price, ReservationStatus status,
                                Review review, String serviceName,
                                Address address, Set<Photo> photos,
                                String cancelationTerms, Double rating) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.price = price;
        this.status = status;
        this.review = review;
        //this.complaint = complaint;
        this.serviceName = serviceName;
        this.address = address;
        this.photos = photos;
        this.cancelationTerms = cancelationTerms;
        this.rating = rating;
    }

    public ReservationSimpleDTO(Reservation r) {
        this(r.getId(), r.getStartDate(), r.getEndDate(), r.getCapacity(),
                r.getPrice(), r.getStatus(), r.getReview(),
                // r.getComplaint()
                r.getService().getName(), r.getService().getAddress(),
                r.getService().getPhotos(), r.getService().getCancellationTerms(),
                r.getService().getRating());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

//    public Complaint getComplaint() {
//        return complaint;
//    }
//
//    public void setComplaint(Complaint complaint) {
//        this.complaint = complaint;
//    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public String getCancelationTerms() {
        return cancelationTerms;
    }

    public void setCancelationTerms(String cancelationTerms) {
        this.cancelationTerms = cancelationTerms;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
