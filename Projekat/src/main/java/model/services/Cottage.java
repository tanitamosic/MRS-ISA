package model.services;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cottages")
public class Cottage {

    @Column(name="number_of_rooms", unique=false, nullable=false)
    private Integer numberOfRooms;

    @Column(name="number_of_beds", unique=false, nullable=false)
    private Integer numberOfBeds;

}
