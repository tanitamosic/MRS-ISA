package com.Projekat.model;

import com.Projekat.model.services.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @SequenceGenerator(name = "promotionIdSeqGen", sequenceName = "promotionIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotionIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id",referencedColumnName = "id")
    private Service service;


    @Column(name="promotion_start", unique=false, nullable=false)
    private LocalDateTime promotionStart;

    @Column(name="promotion_end", unique=false, nullable=false)
    private LocalDateTime promotionEnd;

    @Column(name="reservation_start", unique=false, nullable=false)
    private LocalDateTime reservationStart;

    @Column(name="reservation_end", unique=false, nullable=false)
    private LocalDateTime reservationEnd;

    @Column(name="price", unique=false, nullable=false)
    private Double price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status", unique=false, nullable=false)
    private PromotionStatus status;


}
