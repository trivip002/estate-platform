package com.estate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity
{
    private static final long serialVersionUID = -5426562322680764256L;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private int phoneNumber;

    @Column
    private String email;

    @Column
    private int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false) })
    private List<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "staffs")
    private List<BuildingEntity> buildingAssigns;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<BuildingEntity> buildingPriorities;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<CustomerEntity> customers;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }


    public List<BuildingEntity> getBuildingPriorities() {
        return buildingPriorities;
    }

    public void setBuildingPriorities(List<BuildingEntity> buildingPriorities) {
        this.buildingPriorities = buildingPriorities;
    }

    public List<BuildingEntity> getBuildingAssigns() {
        return buildingAssigns;
    }

    public void setBuildingAssigns(List<BuildingEntity> buildingAssigns) {
        this.buildingAssigns = buildingAssigns;
    }
}
