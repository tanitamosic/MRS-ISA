package com.Projekat.model.loyalty;

import javax.persistence.*;

@Entity
@Table(name="loyalty_program")
public class LoyaltyProgram {
    @Id
    @SequenceGenerator(name = "loyaltyIdSeqGen", sequenceName = "loyaltyIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyaltyIdSeqGen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="client_increment", nullable=false)
    private Integer clientIncrement;

    @Column(name="serviceprovider_increment", nullable = false)
    private Integer serviceProviderIncrement;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="type", nullable=false)
    private LoyaltyRank type;

    @Column(name="start_points", nullable = false)
    private Integer startPoints;

    @Column(name="end_points", nullable = false)
    private Integer endPoints;

    @Column(name="client_discount_percentage", nullable = false)
    private Double clientDiscountPercentage;

    @Column(name="serviceprovider_discount_percentage", nullable = false)
    private Double serviceProviderDiscountPercentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
