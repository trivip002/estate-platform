package com.estate.dto;

import java.util.List;

public class CustomerDTO extends AbstractDTO {
    private static final long serialVersionUID = -437741777829849210L;
    private String name;
    private String phoneNumber;
    private String email;
    private String need;
    private String dateMailing;
    private String process;
    private int status;
    private String note;
    private List<UserDTO> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getDateMailing() {
        return dateMailing;
    }

    public void setDateMailing(String dateMailing) {
        this.dateMailing = dateMailing;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
