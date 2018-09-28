package com.estate.builder;

public class UserBuilder {
    private String role;
    private Long buildingId;

    public String getRole() {
        return role;
    }
    public Long getBuildingId() {
        return buildingId;
    }

    public UserBuilder(Builder builder) {
        this.role = builder.role;
        this.buildingId = builder.buildingId;
    }

    public static class Builder {
        private String role;
        private Long buildingId;

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setBuildingId(Long buildingId) {
            this.buildingId = buildingId;
            return this;
        }

        public UserBuilder build() {
            return new UserBuilder(this);
        }
    }
}
