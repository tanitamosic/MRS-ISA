package com.Projekat.model.services;

import com.Projekat.model.reservations.Reservation;
import com.Projekat.model.users.Client;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @SequenceGenerator(name = "subscriptionIdSeqGen", sequenceName = "subscriptionIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id",referencedColumnName = "id")
    private Service service;

}
