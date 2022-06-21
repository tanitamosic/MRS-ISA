package com.Projekat.dto;

import java.time.LocalDateTime;

public class ActionDTO {

     private Integer serviceId;
     private LocalDateTime actionDateFrom;
     private LocalDateTime actionDateTo;
     private Integer discount;

     public Integer getServiceId() {
          return serviceId;
     }

     public void setServiceId(Integer serviceId) {
          this.serviceId = serviceId;
     }

     public LocalDateTime getActionDateFrom() {
          return actionDateFrom;
     }

     public void setActionDateFrom(LocalDateTime actionDateFrom) {
          this.actionDateFrom = actionDateFrom;
     }

     public LocalDateTime getActionDateTo() {
          return actionDateTo;
     }

     public void setActionDateTo(LocalDateTime actionDateTo) {
          this.actionDateTo = actionDateTo;
     }

     public Integer getDiscount() {
          return discount;
     }

     public void setDiscount(Integer discount) {
          this.discount = discount;
     }
}
