package model;

import javax.persistence.*;

@Entity
@Table(name="addresses")
public class Address {

    @Id
    @SequenceGenerator(name = "adrIdSeqGen", sequenceName = "adrId", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adrId")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;
}
