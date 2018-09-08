package com.estate.Builder;

public class BuildingBuilder {
    private String name;
    private String district;
    private String precint;
    private String street;
    private Integer acreageFloor;
    private String direction;
    private String type;
    private Integer areaFrom;
    private Integer areaTo;
    private Integer priceFrom;
    private Integer priceTo;
    private String managerName;
    private String managerPhone;
    private String[] typeArrays = new String[]{};
    private String staffName;


    public String getStreet() {
        return street;
    }
    public String getPrecint() {
        return precint;
    }
    public String getDirection() {
        return direction;
    }

    public String getType() {
        return type;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public String[] getTypeArrays() {
        return typeArrays;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getAcreageFloor() {
        return acreageFloor;
    }

    public Integer getAreaFrom() {
        return areaFrom;
    }

    public Integer getAreaTo() {
        return areaTo;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public BuildingBuilder(Builder builder) {
        this.name = builder.name;
        this.district = builder.district;
        this.precint = builder.precint;
        this.street = builder.street;
        this.acreageFloor = builder.acreageFloor;
        this.direction = builder.direction;
        this.type = builder.type;
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.priceFrom = builder.priceFrom;
        this.priceTo = builder.priceTo;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.typeArrays = builder.typeArrays;
        this.staffName = builder.staffName;
    }

    public static class Builder {
        private String name;
        private String district;
        private String precint;
        private String street;
        private Integer acreageFloor;
        private String direction;
        private String type;
        private Integer areaFrom;
        private Integer areaTo;
        private Integer priceFrom;
        private Integer priceTo;
        private String managerName;
        private String managerPhone;
        private String[] typeArrays = new String[]{};
        private String staffName;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setPrecint(String precint) {
            this.precint = precint;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Builder setTypeArrays(String[] typeArrays) {
            this.typeArrays = typeArrays;
            return this;
        }

        public Builder setStaffName(String staffName) {
            this.staffName = staffName;
            return this;
        }

        public Builder setAcreageFloor(Integer acreageFloor) {
            this.acreageFloor = acreageFloor;
            return this;
        }

        public Builder setAreaFrom(Integer areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }

        public Builder setAreaTo(Integer areaTo) {
            this.areaTo = areaTo;
            return this;
        }

        public Builder setPriceFrom(Integer priceFrom) {
            this.priceFrom = priceFrom;
            return this;
        }

        public Builder setPriceTo(Integer priceTo) {
            this.priceTo = priceTo;
            return this;
        }

        public BuildingBuilder build() {
            return new BuildingBuilder(this);
        }



    }
}