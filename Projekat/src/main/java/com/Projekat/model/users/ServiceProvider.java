package com.Projekat.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class ServiceProvider extends User {

    @Column(name = "loyalty_score")
    private Integer loyaltyScore;

}
