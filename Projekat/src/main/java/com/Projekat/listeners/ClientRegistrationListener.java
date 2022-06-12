package com.Projekat.listeners;

import com.Projekat.events.OnClientRegistrationEvent;
import com.Projekat.events.OnEmployeeRegistrationEvent;
import com.Projekat.model.Account;
import com.Projekat.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientRegistrationListener implements ApplicationListener<OnClientRegistrationEvent> {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnClientRegistrationEvent event) {
        this.sendRegistrationConfirmationEmail(event);
    }




    private void sendRegistrationConfirmationEmail(OnClientRegistrationEvent event) {
        Account acc = event.getAccount();
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(acc, token);

        String recipientAddress = acc.getUsername();
        String subject = "Potvrda registracije";
        String confirmationUrl = event.getAppUrl() + "/regitrationConfirm?token=" + token;
        String message = "Kliknite na link da bi ste aktivirali svoj nalog.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:3000/auth/" + confirmationUrl);
        mailSender.send(email);
    }
}
