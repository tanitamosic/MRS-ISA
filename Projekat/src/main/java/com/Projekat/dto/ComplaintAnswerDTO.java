package com.Projekat.dto;

public class ComplaintAnswerDTO {

    private Integer complaintId;
    private String answerToClient;
    private Integer targetClient;
    private String answerToEmployee;
    private Integer targetEmployee;

    public ComplaintAnswerDTO(Integer id, String answerToClient, Integer targetClient, String answerToEmployee,
                              Integer targetEmployee) {
        this.complaintId = id;
        this.answerToClient = answerToClient;
        this.targetClient = targetClient;
        this.answerToEmployee = answerToEmployee;
        this.targetEmployee = targetEmployee;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public ComplaintAnswerDTO() {
    }

    public String getAnswerToClient() {
        return answerToClient;
    }

    public void setAnswerToClient(String answerToClient) {
        this.answerToClient = answerToClient;
    }

    public Integer getTargetClient() {
        return targetClient;
    }

    public void setTargetClient(Integer targetClient) {
        this.targetClient = targetClient;
    }

    public String getAnswerToEmployee() {
        return answerToEmployee;
    }

    public void setAnswerToEmployee(String answerToEmployee) {
        this.answerToEmployee = answerToEmployee;
    }

    public Integer getTargetEmployee() {
        return targetEmployee;
    }

    public void setTargetEmployee(Integer targetEmployee) {
        this.targetEmployee = targetEmployee;
    }
}
