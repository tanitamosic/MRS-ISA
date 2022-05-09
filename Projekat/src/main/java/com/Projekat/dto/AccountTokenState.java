package com.Projekat.dto;

public class AccountTokenState {

    private String accessToken;
    private Long expiresIn;

    public AccountTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public AccountTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}