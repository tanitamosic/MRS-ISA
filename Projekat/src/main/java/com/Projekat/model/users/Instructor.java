package com.Projekat.model.users;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IN")
public class Instructor extends ServiceProvider {

    @Column(name = "biography")
    private String biography;
}
