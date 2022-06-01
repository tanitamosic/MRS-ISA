package com.Projekat.dto;

public class UserDTO {

    public String Email;
    public String Password1;
    public String Password2;

    public String Name;
    public String Surname;
    public String Phone;

    public String City;
    public String Street;
    public String State;

    public String getCity() {
        return City;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "Email='" + Email + '\'' +
                ", Password1='" + Password1 + '\'' +
                ", Password2='" + Password2 + '\'' +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Phone='" + Phone + '\'' +
                ", City='" + City + '\'' +
                ", Street='" + Street + '\'' +
                ", State='" + State + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                '}';
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

    public String getState() {
        return State;
    }

    public void setState(String streetNumber) {
        State = streetNumber;
    }

    public String DateOfBirth;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword1() {
        return Password1;
    }

    public void setPassword1(String password1) {
        Password1 = password1;
    }

    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String password2) {
        Password2 = password2;
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

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public UserDTO() {
    }
}
