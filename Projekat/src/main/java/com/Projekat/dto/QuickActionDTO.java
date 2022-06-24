package com.Projekat.dto;

public class QuickActionDTO {

    private Integer quickActionId;
    private Integer serviceId;
    private String type;

    public QuickActionDTO() {}

    public Integer getQuickActionId() {
        return quickActionId;
    }

    public void setQuickActionId(Integer quickActionId) {
        this.quickActionId = quickActionId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
