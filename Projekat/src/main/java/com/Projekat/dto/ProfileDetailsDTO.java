package com.Projekat.dto;

public class ProfileDetailsDTO {
    public String Email;
    public String OldPassword;
    public String NewPassword1;
    public String NewPassword2;

    public String Name;
    public String Surname;
    public String Phone;
    public String Address;
    public String Biography;

    public ProfileDetailsDTO(String email, String oldPassword, String newPassword1, String newPassword2, String name,
                             String surname, String phone, String address) {
        Email = email;
        OldPassword = oldPassword;
        NewPassword1 = newPassword1;
        NewPassword2 = newPassword2;
        Name = name;
        Surname = surname;
        Phone = phone;
        Address = address;
    }

    public ProfileDetailsDTO(String email, String oldPassword, String newPassword1, String newPassword2, String name,
                             String surname, String phone, String address, String biography) {
        Email = email;
        OldPassword = oldPassword;
        NewPassword1 = newPassword1;
        NewPassword2 = newPassword2;
        Name = name;
        Surname = surname;
        Phone = phone;
        Address = address;
        Biography = biography;
    }

    public ProfileDetailsDTO() {
    }
}
