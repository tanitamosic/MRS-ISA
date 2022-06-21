package com.Projekat.model.services;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="quick_action")
public class QuickAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Integer discount;

    @JoinColumn(name="service_id", nullable = false)
    @ManyToOne(targetEntity = Service.class, fetch = FetchType.EAGER)
    private Service service;

    public QuickAction(LocalDateTime dateFrom, LocalDateTime dateTo, Integer discount, Service service) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.discount = discount;
        this.service = service;
    }

    public QuickAction() {
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
