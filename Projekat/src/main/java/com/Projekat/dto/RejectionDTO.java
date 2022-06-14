package com.Projekat.dto;

public class RejectionDTO {

    public Integer acc_id;
    public String Explanation;

    public Integer getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(Integer acc_id) {
        this.acc_id = acc_id;
    }

    public RejectionDTO(Integer acc_id, String explanation) {
        this.acc_id = acc_id;
        this.Explanation = explanation;
    }

    public RejectionDTO() {
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        this.Explanation = explanation;
    }
}
