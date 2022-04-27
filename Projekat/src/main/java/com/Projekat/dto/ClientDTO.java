package com.Projekat.dto;

import com.Projekat.model.Address;
import com.Projekat.model.users.Client;

public class ClientDTO {

    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private Address address;
    private Integer penalties;
    private Integer loyaltyScore;

    public ClientDTO() {}

    public ClientDTO(Integer id, String name,
                     String surname, String phone, Address address,
                     Integer penalties,
                     Integer loyaltyScore) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.penalties = penalties;
        this.loyaltyScore = loyaltyScore;
    }

    public ClientDTO(Client c){
        this(c.getId(), c.getName(), c.getSurname(),
                c.getPhone(), c.getAddress(),
                c.getPenalties(), c.getLoyaltyScore());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public Integer getLoyaltyScore() {
        return loyaltyScore;
    }
}
