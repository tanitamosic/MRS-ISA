package com.Projekat.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="employee_reg_request")
public class EmployeeRegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "acc_id")
    private Account account;
    private String username;
    private String requestDate;
    private String registrationParams;
    private boolean accepted;
    private boolean rejected;

    public EmployeeRegistrationRequest(Account acc, String registrationParams, String username) {
        this.account = acc;
        this.username = username;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.requestDate = dateFormat.format(new Date());
        this.registrationParams = registrationParams;
        this.accepted = false;
        this.rejected = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRegistrationParams() {
        return registrationParams;
    }

    public void setRegistrationParams(String registrationParams) {
        this.registrationParams = registrationParams;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean resolved) {
        this.accepted = resolved;
    }

    public EmployeeRegistrationRequest() {

    }
}
