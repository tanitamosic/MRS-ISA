package com.Projekat.model;

import com.Projekat.model.reservations.Reservation;

import javax.persistence.*;

@Entity
@Table(name="profit")
public class Profit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "res_id")
    private Reservation reservation;

    @Column
    private Integer totalPrice;
    @Column
    private Integer priceAfterCut;
    @Column
    private Integer priceCut;

    public Profit(Reservation reservation, Integer totalPrice, Integer priceAfterCut, Integer priceCut) {
        this.reservation = reservation;
        this.totalPrice = totalPrice;
        this.priceAfterCut = priceAfterCut;
        this.priceCut = priceCut;
    }

    public Profit() {
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPriceAfterCut() {
        return priceAfterCut;
    }

    public void setPriceAfterCut(Integer priceAfterCut) {
        this.priceAfterCut = priceAfterCut;
    }

    public Integer getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(Integer priceCut) {
        this.priceCut = priceCut;
    }
}
