package com.estate.builder;

public class BuildingBuilder {

    private String name;
    private String district;

    public BuildingBuilder(Builder builder) {
        this.name = builder.name;
        this.district = builder.district;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public static class Builder {
        private String name;
        private String district;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public BuildingBuilder build() {
            return new BuildingBuilder(this);
        }
    }
}
