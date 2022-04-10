package com.Projekat.model.reservations;

import com.Projekat.model.Address;

import javax.persistence.*;

@Entity
@DiscriminatorValue("AR")
public class AdventureReservation extends Reservation {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public Address getAddress() {
        return address;
    }
}
