package com.Projekat.model.services;

import com.Projekat.dto.AdventureDTO;
import com.Projekat.model.Address;
import com.Projekat.model.users.ServiceProvider;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "adventures")
public class Adventure extends Service {

//    @Id
//    @SequenceGenerator(name = "advIdSeqGen", sequenceName = "advId", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advIdSeqGen")
//    @Column(name="id", unique=true, nullable=false)
//    private Integer id;

    @Column(name="capacity", unique=false, nullable=false)
    private Integer capacity;

    @Column(name="fishing_equipment", unique=false, nullable=true)
    private String fishingEquipment;

//    public Integer getId() {
//        return id;
//    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public Adventure() {
    }

    public Adventure(AdventureDTO a, Address address, ServiceProvider owner, Set<AdditionalService> additionalServices) {
        this.setAddress(address);
        this.setName(a.getName());
        this.setCapacity(a.getCapacity());
        this.setFishingEquipment(a.getFishingEq());
        this.setCancellationTerms(a.getCancelTerms());
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
