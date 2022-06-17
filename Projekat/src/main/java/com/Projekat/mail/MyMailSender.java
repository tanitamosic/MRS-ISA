package com.Projekat.mail;

import com.Projekat.model.reservations.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendDelRejectMail(String target, String explanation) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject("Obaveštenje o zahtevu za odjavu sa TurboJavaSpringbootTurizamExpo");
        email.setText("Na žalost\r\n, Vaš zahtev za brisanje naloga je odbijen iz navedenih razloga:\r\n" + explanation);
        mailSender.send(email);
    }

    public void sendAccDeletionMail(String target) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject("Brisanje naloga uspešno");
        email.setText("Vaš zahtev za brisanje naloga je prihvaćen!\r\nŽao nam je što nam odlazite.\r\nPoz");
        mailSender.send(email);
    }

    public void sendRegRejectMail(String target, String explanation) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject("Obaveštenje o registraciji na TurboJavaSpringbootTurizamExpo website");
        email.setText("Na žalost\r\n, Vaš zahtev za registraciju je odbijen iz navedenih razloga:\r\n" + explanation);
        mailSender.send(email);
    }

    public void sendRegAcceptMail(String target) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject("Obaveštenje o registraciji na TurboJavaSpringbootTurizamExpo website");
        email.setText("Čestitamo,\r\n Vaš zahtev za registraciju je prihvaćen!\r\nDobro došli u TurboJavaSpringbootTurizamExpo!!");
        mailSender.send(email);
    }

    public void sendComplaintAnswer(String target, String answer) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject("Obaveštenje o žalbi na TurboJavaSpringbootTurizamExpo");
        email.setText(answer);
        mailSender.send(email);
    }

    public void sendSucessfulCottageReservation(String target, String mail_body) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject("Obaveštenje o izvršenoj rezervaciji na TurboJavaSpringbootTurizamExpo");
        email.setText(mail_body);
        mailSender.send(email);
    }



}
