package com.Projekat.model.reservations.submitions;

import com.Projekat.model.reservations.Reservation;

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

    @Column(name="answer", nullable = true)
    private String answer;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status", nullable = false)
    private ComplaintStatus status;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name="reservation_id", referencedColumnName = "id")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
