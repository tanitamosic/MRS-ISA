package com.Projekat.model;
import com.Projekat.model.users.User;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @SequenceGenerator(name = "accIdSeqGen", sequenceName = "accId", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "activated", nullable = false)
    private Boolean isActivated;

    @Column(name = "deleted", nullable = false)
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
