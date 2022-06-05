package com.Projekat.dto;

public class ProfileDetailsDTO {

    public Integer Id;

    public String OldUsername;
    public String NewUsername;
    public String NewPassword;

    public String Name;
    public String Surname;
    public String Phone;
    public String State;
    public String City;
    public String Street;

    private String Biography;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getOldUsername() {
        return OldUsername;
    }

    public void setOldUsername(String oldUsername) {
        OldUsername = oldUsername;
    }

    public String getNewUsername() {
        return NewUsername;
    }

    public void setNewUsername(String newUsername) {
        NewUsername = newUsername;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String biography) {
        Biography = biography;
    }

    public boolean DidTryPasswordUpdate() {
        return !NewPassword.equals("");
    }

    public boolean DidTryUsernameUpdate() {
        return !OldUsername.equals(NewUsername);
    }

}
