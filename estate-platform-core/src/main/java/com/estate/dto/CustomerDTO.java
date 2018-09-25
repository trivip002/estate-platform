package com.estate.dto;

import java.util.List;

public class CustomerDTO extends AbstractDTO {

    private static final long serialVersionUID = -4649581006161336386L;
    private String name;
    private String phoneNumber;
    private String email;
    private String need;
    private String dateMail;
    private String process;
    private int status;
    private String note;
    private List<UserDTO> users;
    private List<CustomerServiceDTO> careDetail;
    private String staffName;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

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

    public String getDateMail() {
        return dateMail;
    }

    public void setDateMail(String dateMail) {
        this.dateMail = dateMail;
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



    public List<CustomerServiceDTO> getCareDetail() {
        return careDetail;
    }

    public void setCareDetail(List<CustomerServiceDTO> careDetail) {
        this.careDetail = careDetail;
    }
}
