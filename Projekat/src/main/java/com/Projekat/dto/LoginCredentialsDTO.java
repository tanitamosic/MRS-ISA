package com.Projekat.dto;

public class LoginCredentialsDTO {

    public String Email;
    public String Password;

    public LoginCredentialsDTO() {}

    public LoginCredentialsDTO(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
