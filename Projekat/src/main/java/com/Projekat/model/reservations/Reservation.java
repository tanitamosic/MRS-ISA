package com.Projekat.model.reservations;

import com.Projekat.model.services.Service;
import com.Projekat.model.reservations.submitions.Review;
import com.Projekat.model.users.Client;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resSeqGen")
    @SequenceGenerator(name = "resSeqGen", sequenceName = "resSeq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name="end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name="capacity", nullable = false)
    private Integer capacity;

    @Column(name="price", nullable = false)
    private Double price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status", nullable = false)
    private ReservationStatus status;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id",referencedColumnName = "id")
    private Review review;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}