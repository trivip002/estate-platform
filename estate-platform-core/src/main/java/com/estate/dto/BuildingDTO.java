package com.estate.dto;

import java.util.List;
import java.util.Map;

public class BuildingDTO extends AbstractDTO<BuildingDTO> {
    private static final long serialVersionUID = 4428522696190004907L;
    private String name;
    private String precint;
    private String street;
    private String structure;
    private String direction;
    private String ranking;
    private String acreageDescription;
    private String priceDescription;
    private String rentDuration;
    private String decorateTime;
    private String note;
    private String link;
    private String map;
    private String thumnail;
    private String imageName;
    private String thumbnailBase64;
    private String basementNumber;
    private Integer acreageFloor;
    private Integer acreageRent;
    private Integer rentCost;
    private String serviceCharge;
    private String carCharge;
    private String overtimeCharge;
    private String electricBill;
    private String deposit;
    private String pay;
    private String agencyCharge;
    private String types;
    private String district;
    private Map<String,String> districts;
    private int priority = 0;
    private String[] typeArray = new String[]{};
    private List<UserDTO> staffs;
    private List<UserDTO> users;
    private Integer areaFrom;
    private Integer areaTo;
    private Integer priceFrom;
    private Integer priceTo;
    private String managerName;
    private String managerPhone;
    private String staffName;

    public Integer getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(Integer areaFrom) {
        this.areaFrom = areaFrom;
    }

    public Integer getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(Integer areaTo) {
        this.areaTo = areaTo;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getName() {
        return name;
    }


    public Map<String, String> getDistricts() {
        return districts;
    }

    public void setDistricts(Map<String, String> districts) {
        this.districts = districts;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getThumbnailBase64() {
        if (thumbnailBase64 == null) {
            return thumbnailBase64;
        }
        return thumbnailBase64.split(",")[1];
    }

    public void setThumbnailBase64(String thumbnailBase64) {
        this.thumbnailBase64 = thumbnailBase64;
    }

    public List<UserDTO> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserDTO> staffs) {
        this.staffs = staffs;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public String[] getTypeArray() {
        return typeArray;
    }

    public void setTypeArray(String[] typeArray) {
        this.typeArray = typeArray;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
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
