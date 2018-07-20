package com.estate.dto;

import java.util.List;
import java.util.Map;

public class UserDTO extends AbstractDTO<UserDTO>
{
    private String userName;
    private String password;
    private String fullName;
    private String status;
    private int phoneNumber;
    private String email;
    private String role;
    private List<RoleDTO> roles;
    private Map<String,String> rolesName;
    private String error;
    private List<BuildingDTO> buildingAssigns;
    private List<BuildingDTO> buildingPriorities;
    private List<CustomerDTO> customers;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }


    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getRolesName() {
        return rolesName;
    }

    public void setRolesName(Map<String, String> rolesName) {
        this.rolesName = rolesName;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    public List<BuildingDTO> getBuildingAssigns() {
        return buildingAssigns;
    }

    public void setBuildingAssigns(List<BuildingDTO> buildingAssigns) {
        this.buildingAssigns = buildingAssigns;
    }

    public List<BuildingDTO> getBuildingPriorities() {
        return buildingPriorities;
    }

    public void setBuildingPriorities(List<BuildingDTO> buildingPriorities) {
        this.buildingPriorities = buildingPriorities;
    }
}
