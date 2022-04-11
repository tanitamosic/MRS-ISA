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

    @Column(name="fishing_equipment", unique=false, nullable=false)
    private String fishingEquipment;

}
