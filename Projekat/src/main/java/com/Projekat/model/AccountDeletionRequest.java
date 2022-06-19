package com.Projekat.model;

import com.Projekat.model.users.User;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="acc_del_requests")
public class AccountDeletionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "acc_id")
    private Account account;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usr_id")
    private User user;

    private boolean accepted;
    private String deletionRequest;
    private boolean rejected;
    private String requestDate;

    public Account getAccount() {
        return account;
    }

    public AccountDeletionRequest(Account account, User usr, String deletionRequest) {
        this.deletionRequest = deletionRequest;
        this.account = account;
        this.user = usr;
        this.accepted = false;
        this.rejected = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.requestDate = dateFormat.format(new Date());
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getDeletionRequest() {
        return deletionRequest;
    }

    public void setDeletionRequest(String deletionRequest) {
        this.deletionRequest = deletionRequest;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public AccountDeletionRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User usr) {
        this.user = usr;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
