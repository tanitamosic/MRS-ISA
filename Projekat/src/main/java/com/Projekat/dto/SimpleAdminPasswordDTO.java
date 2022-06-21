package com.Projekat.dto;

public class SimpleAdminPasswordDTO {

    private Integer usr_id;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SimpleAdminPasswordDTO(String password, Integer usr_id) {
        this.password = password;
        this.usr_id = usr_id;
    }

    public SimpleAdminPasswordDTO() {
    }

    public Integer getUsrId() {
        return usr_id;
    }

    public void setUsr_id(Integer usr_id) {
        this.usr_id = usr_id;
    }
}
