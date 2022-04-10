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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch=FetchType.LAZY)
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
}