package com.Projekat.model.services;

import com.Projekat.dto.AdventureDTO;
import com.Projekat.dto.CottageDTO;
import com.Projekat.model.Address;
import com.Projekat.model.users.ServiceProvider;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cottages")
public class Cottage extends Service {

//    @Id
//    @SequenceGenerator(name = "cotIdSeqGen", sequenceName = "cotId", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotIdSeqGen")
//    @Column(name="id", unique=true, nullable=false)
//    private Integer id;

    @Column(name="number_of_rooms", unique=false, nullable=false)
    private Integer numberOfRooms;

    @Column(name="number_of_beds", unique=false, nullable=false)
    private Integer numberOfBeds;

//    public Integer getId() {
//        return id;
//    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

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

    public Cottage(){}

    public Cottage(CottageDTO a, Address address, ServiceProvider owner, Set<AdditionalService> additionalServices) {
        this.setAddress(address);
        this.setName(a.getName());
        this.setNumberOfRooms(a.getNumberOfRooms());
        this.setNumberOfBeds(a.getNumberOfBeds());
        this.setCancellationTerms(a.getCancellationTerms());
        this.setDescription(a.getDescription());
        this.setPrice(a.getPrice());
        this.setRules(a.getRules());

        this.setDeleted(false);
        this.setAdditionalServices(additionalServices);
        this.setOwner(owner);
        this.setAvailabilityStart(a.getAvailabilityStart());
        this.setAvailabilityEnd(a.getAvailabilityEnd());
        this.setRating(0.0);
    }

}
