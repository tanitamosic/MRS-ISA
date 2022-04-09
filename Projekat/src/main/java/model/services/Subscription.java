package model.services;

import model.users.Client;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @SequenceGenerator(name = "subscriptionIdSeqGen", sequenceName = "subscriptionIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @OneToOne(mappedBy = "id")
    private Client client;

    @OneToOne(mappedBy = "id")
    private Service service;

}
