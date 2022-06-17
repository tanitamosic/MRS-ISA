package com.Projekat.model.services;

import javax.persistence.*;

@Entity
@Table(name="additional_services")
public class AdditionalService {

    @Id
    @SequenceGenerator(name = "asIdSeqGen", sequenceName = "asIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @Column(name="price", unique = false, nullable = false)
    private Double price;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
