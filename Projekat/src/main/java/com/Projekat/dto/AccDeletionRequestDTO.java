package com.Projekat.dto;

public class AccDeletionRequestDTO {

    private Integer id;
    private String deletionRequest;

    public String getDeletionRequest() {
        return deletionRequest;
    }

    public void setDeletionRequest(String deletionRequest) {
        this.deletionRequest = deletionRequest;
    }

    public AccDeletionRequestDTO() {
    }

    public AccDeletionRequestDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
