package com.Projekat.model.services;

import javax.persistence.*;

@Entity
@Table(name = "cottages")
public class Cottage extends Service {

    @Id
    @SequenceGenerator(name = "cotIdSeqGen", sequenceName = "cotId", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="number_of_rooms", unique=false, nullable=false)
    private Integer numberOfRooms;

    @Column(name="number_of_beds", unique=false, nullable=false)
    private Integer numberOfBeds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

}
