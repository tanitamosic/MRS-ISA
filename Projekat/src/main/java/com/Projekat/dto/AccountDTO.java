package com.Projekat.dto;

public class AccountDTO {

    private Integer id;

    private String username;

    private String password;

    private Boolean isActivated;

    private Boolean isDeleted;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActivated() { return isActivated; }

    public void setActivated(Boolean activated) { isActivated = activated; }

    public Boolean getDeleted() { return isDeleted; }

    public void setDeleted(Boolean deleted) { isDeleted = deleted; }
}