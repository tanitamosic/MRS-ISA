package com.Projekat.model.users;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CL")
public class Client extends User{

    @Column(name = "penalties")
    private Integer penalties;

    @Column(name = "loyalty_score")
    private Integer loyaltyScore;


    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public Integer getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(Integer loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }
}
