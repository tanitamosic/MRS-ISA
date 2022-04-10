package com.Projekat.model.reservations.submitions;

import com.Projekat.model.reservations.Reservation;

import javax.persistence.*;

@Entity
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSeqGen")
    @SequenceGenerator(name = "reportSeqGen", sequenceName = "reportSeq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="comment", nullable = false)
    private String comment;

    @Column(name="is_bad", nullable = false)
    private boolean isBad;

    @Column(name="no_show", nullable = false)
    private boolean noShow;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status", nullable = true)
    private ReportStatus status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy="id")
    private Reservation reservation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
