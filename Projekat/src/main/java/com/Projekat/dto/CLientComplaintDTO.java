package com.Projekat.dto;

public class CLientComplaintDTO {

    private Integer reservationId;
    private String complaintText;

    public CLientComplaintDTO() {}

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }
}
