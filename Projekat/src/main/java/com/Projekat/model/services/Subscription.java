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

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private Client client;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id",referencedColumnName = "id")
    private Service service;


    public Subscription() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
