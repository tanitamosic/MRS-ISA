package com.Projekat.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BO")
public class BoatOwner extends ServiceProvider {

}
