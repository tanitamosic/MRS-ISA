package com.Projekat.model.services;

import javax.persistence.*;

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

    @Column(name="fishing_equipment", unique=false, nullable=false)
    private String fishingEquipment;

}
