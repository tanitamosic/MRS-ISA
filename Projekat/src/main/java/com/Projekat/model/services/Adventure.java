package com.Projekat.model.services;

import javax.persistence.*;

@Entity
@Table(name = "adventures")
public class Adventure extends Service {

    @Id
    @SequenceGenerator(name = "advIdSeqGen", sequenceName = "advId", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="capacity", unique=false, nullable=false)
    private Integer capacity;

    @Column(name="fishing_equipment", unique=false, nullable=true)
    private String fishingEquipment;

    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
