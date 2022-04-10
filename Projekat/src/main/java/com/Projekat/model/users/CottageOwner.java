package com.Projekat.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CO")
public class CottageOwner extends ServiceProvider {

}
