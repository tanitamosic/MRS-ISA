package model.reservations;

import model.Address;

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
