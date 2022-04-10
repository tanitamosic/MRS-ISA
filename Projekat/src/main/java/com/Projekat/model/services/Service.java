package com.Projekat.model.services;

import com.Projekat.model.Address;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract class Service {

    @Id
    @SequenceGenerator(name = "serviceIdSeqGen", sequenceName = "serviceIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name="description", unique=false, nullable=false)
    private String description;

    // TODO: pictures

    @Column(name="rules", unique=false, nullable=false)
    private String rules;

    @Column(name="price", unique=false, nullable=false)
    private Double price;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "additional_services",
               joinColumns = {
                    @JoinColumn(name = "service_id")
               },
               inverseJoinColumns = {
                    @JoinColumn(name = "additional_id")
               }
    )
    Set<AdditionalService> additionalServices;


    @Column(name="cancelation_terms", unique=false, nullable=false)
    private String cancelationTerms;

    @Column(name="availability_start", unique=false, nullable=false)
    private LocalDateTime availabilityStart;

    @Column(name="availability_end", unique=false, nullable=false)
    private LocalDateTime availabilityEnd;

    @Column(name="is_deleted", unique=false, nullable=false)
    private Boolean isDeleted;

}
