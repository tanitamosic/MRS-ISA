package com.Projekat.model.services;

import com.Projekat.dto.BoatDTO;
import com.Projekat.model.Address;
import com.Projekat.model.users.ServiceProvider;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "boats")
public class Boat extends Service{

//    @Id
//    @SequenceGenerator(name = "boatIdSeqGen", sequenceName = "boatId", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boatIdSeqGen")
//    @Column(name="id", unique=true, nullable=false)
//    private Integer id;

    @Column(name="type", unique=false, nullable=false)
    private String type;

    @Column(name="length", unique=false, nullable=false)
    private Double length;

    @Column(name="number_of_engines", unique=false, nullable=false)
    private Integer numberOfEngines;

    @Column(name="engine_power", unique=false, nullable=false)
    private Double enginePower;

    @Column(name="max_speed", unique=false, nullable=false)
    private Double maxSpeed;

    @Column(name="navigational_equipment", unique=false, nullable=false)
    private String navigationalEquipment;

    @Column(name="capacity", unique=false, nullable=false)
    private Integer capacity;

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setNumberOfEngines(Integer numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setNavigationalEquipment(String navigationalEquipment) {
        this.navigationalEquipment = navigationalEquipment;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    @Column(name="fishing_equipment", unique=false, nullable=false)
    private String fishingEquipment;

    public String getType() {
        return type;
    }

    public Double getLength() {
        return length;
    }

    public Integer getNumberOfEngines() {
        return numberOfEngines;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public String getNavigationalEquipment() {
        return navigationalEquipment;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public Boat(){

    }

    public Boat(BoatDTO a, Address address, ServiceProvider owner, Set<AdditionalService> additionalServices) {
        this.setAddress(address);
        this.setName(a.getName());
        this.setCapacity(a.getCapacity());
        this.setFishingEquipment(a.getFishingEquipment());
        this.setCancellationTerms(a.getCancellationTerms());
        this.setDescription(a.getDescription());
        this.setPrice(a.getPrice());
        this.setRules(a.getRules());
        this.setNavigationalEquipment(a.getNavigationalEquipment());
        this.setOwner(owner);
        this.setType(a.getType());
        this.setLength(a.getLength());
        this.setNumberOfEngines(a.getNumberOfEngines());
        this.setEnginePower(a.getEnginePower());
        this.setMaxSpeed(a.getMaxSpeed());

        this.setDeleted(false);
        this.setAdditionalServices(additionalServices);
        this.setOwner(owner);
        this.setAvailabilityStart(a.getAvailabilityStart());
        this.setAvailabilityEnd(a.getAvailabilityEnd());
        this.setRating(0.0);
    }

}
