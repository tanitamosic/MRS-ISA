package model.reservations.submitions;

import model.reservations.Reservation;

import javax.persistence.*;

@Entity
@Table(name="complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comSeqGen")
    @SequenceGenerator(name = "comSeqGen", sequenceName = "comSeq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="answer", nullable = false)
    private String answer;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status", nullable = false)
    private ComplaintStatus status;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="id")
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
