package com.Projekat.model.reservations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CR")
public class CottageReservation extends Reservation {
}
