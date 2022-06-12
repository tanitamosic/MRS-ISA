package com.Projekat.model;

import javax.persistence.*;
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
    private Date requestDate;
    private String registrationParams;
    private boolean resolved;

    public EmployeeRegistrationRequest(Account acc, String registrationParams, String username) {
        this.account = acc;
        this.username = username;
        this.requestDate = new Date();
        this.registrationParams = registrationParams;
        this.resolved = false;
    }

    public EmployeeRegistrationRequest() {

    }
}
