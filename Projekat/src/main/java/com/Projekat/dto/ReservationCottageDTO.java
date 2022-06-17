package com.Projekat.dto;

import com.Projekat.model.reservations.ReservationStatus;
import com.Projekat.model.reservations.submitions.Review;
import com.Projekat.model.services.AdditionalService;
import com.Projekat.model.services.Cottage;
import com.Projekat.model.services.Service;
import com.Projekat.model.users.Client;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationCottageDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
    private Integer capacity;
    private Integer serviceId;

    private Set<Integer> additionalServices;

    public ReservationCottageDTO() {}

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

    public Set<Integer> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<Integer> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
