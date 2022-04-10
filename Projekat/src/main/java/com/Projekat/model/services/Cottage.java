package com.Projekat.model.services;

import javax.persistence.*;

@Entity
@Table(name = "cottages")
public class Cottage {

    @Id
    @SequenceGenerator(name = "cotIdSeqGen", sequenceName = "cotId", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="number_of_rooms", unique=false, nullable=false)
    private Integer numberOfRooms;

    @Column(name="number_of_beds", unique=false, nullable=false)
    private Integer numberOfBeds;

}
