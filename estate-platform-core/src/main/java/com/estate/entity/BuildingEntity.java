package com.estate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    private static final long serialVersionUID = -2472658315407497223L;

    @Column
    private String name;
    @Column
    private String precint;
    @Column
    private String district;
    @Column
    private String street;
    @Column
    private String structure;
    @Column
    private String direction;
    @Column
    private String ranking;
    @Column
    private String acreageDescription;
    @Column
    private String priceDescription;
    @Column
    private String rentDuration;
    @Column
    private String decorateTime;
    @Column
    private String note;
    @Column
    private String link;
    @Column
    private String types;
    @Column
    private String map;
    @Column
    private String thumnail;
    @Column
    private String basementNumber;
    @Column
    private Integer acreageFloor;
    @Column
    private Integer acreageRent;
    @Column
    private Integer rentCost;
    @Column
    private String serviceCharge;
    @Column
    private String carCharge;
    @Column
    private String overtimeCharge;
    @Column
    private String electricBill;
    @Column
    private String deposit;
    @Column
    private String pay;
    @Column
    private String agencyCharge;
    @Column
    private int priority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "assignment", joinColumns = {
            @JoinColumn(name = "building_id", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "staff_id", nullable = false) })
    private List<UserEntity> staffs = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "priority", joinColumns = {
            @JoinColumn(name = "building_id", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "staff_id", nullable = false) })
    private List<UserEntity> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecint() {
        return precint;
    }

    public void setPrecint(String precint) {
        this.precint = precint;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getAcreageDescription() {
        return acreageDescription;
    }

    public void setAcreageDescription(String acreageDescription) {
        this.acreageDescription = acreageDescription;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public String getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(String rentDuration) {
        this.rentDuration = rentDuration;
    }

    public String getDecorateTime() {
        return decorateTime;
    }

    public void setDecorateTime(String decorateTime) {
        this.decorateTime = decorateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public String getBasementNumber() {
        return basementNumber;
    }

    public void setBasementNumber(String basementNumber) {
        this.basementNumber = basementNumber;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getCarCharge() {
        return carCharge;
    }

    public void setCarCharge(String carCharge) {
        this.carCharge = carCharge;
    }

    public String getOvertimeCharge() {
        return overtimeCharge;
    }

    public void setOvertimeCharge(String overtimeCharge) {
        this.overtimeCharge = overtimeCharge;
    }

    public String getElectricBill() {
        return electricBill;
    }

    public void setElectricBill(String electricBill) {
        this.electricBill = electricBill;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getAgencyCharge() {
        return agencyCharge;
    }

    public void setAgencyCharge(String agencyCharge) {
        this.agencyCharge = agencyCharge;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getAcreageFloor() {
        return acreageFloor;
    }

    public void setAcreageFloor(Integer acreageFloor) {
        this.acreageFloor = acreageFloor;
    }

    public Integer getAcreageRent() {
        return acreageRent;
    }

    public void setAcreageRent(Integer acreageRent) {
        this.acreageRent = acreageRent;
    }

    public Integer getRentCost() {
        return rentCost;
    }

    public void setRentCost(Integer rentCost) {
        this.rentCost = rentCost;
    }
}
