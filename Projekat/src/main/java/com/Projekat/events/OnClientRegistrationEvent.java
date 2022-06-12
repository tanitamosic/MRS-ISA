package com.Projekat.events;

import com.Projekat.model.Account;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnClientRegistrationEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private Account account;

    public OnClientRegistrationEvent(
            Account acc, Locale locale, String appUrl) {
        super(acc);

        this.account = acc;
        this.locale = locale;
        this.appUrl = appUrl;
    }


    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Account getAccount() {
        return account;
    }

    public void setUser(Account acc) {
        this.account = acc;
    }


}
