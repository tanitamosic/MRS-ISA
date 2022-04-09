package model.reservations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BR")
public class BoatReservation extends Reservation {
}
