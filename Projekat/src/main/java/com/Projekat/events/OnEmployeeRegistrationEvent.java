package com.Projekat.events;

import com.Projekat.model.Account;
import org.springframework.context.ApplicationEvent;

public class OnEmployeeRegistrationEvent extends ApplicationEvent {

    private Account account;
    private String registrationRequest;
    private String username;
    public OnEmployeeRegistrationEvent(Account acc, String registrationRequest, String username) {
        super(acc);
        this.account = acc;
        this.registrationRequest = registrationRequest;
        this.username = username;
    }

    public Account getAccount() {
        return account;
    }

    public String getUsername() {
        return username;
    }

    public String getRegistrationRequest() {
        return registrationRequest;
    }
}
