package com.Projekat.model.services;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Entity
@Table(name="quick_action")
public class QuickAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Integer discount;
    private QuickActionStatus status;


    public QuickAction(LocalDateTime dateFrom, LocalDateTime dateTo, Integer discount) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.discount = discount;
        this.status = QuickActionStatus.ACTIVE;
    }

    public QuickAction() {}

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

    public QuickActionStatus getStatus() {
        return status;
    }

    public void setStatus(QuickActionStatus status) {
        this.status = status;
    }
}
