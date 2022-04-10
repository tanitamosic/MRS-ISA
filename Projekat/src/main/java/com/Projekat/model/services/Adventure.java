package com.Projekat.model.services;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adventures")
public class Adventure {

    @Column(name="capacity", unique=false, nullable=false)
    private Integer capacity;

    @Column(name="fishing_equipment", unique=false, nullable=false)
    private String fishingEquipment;

}
