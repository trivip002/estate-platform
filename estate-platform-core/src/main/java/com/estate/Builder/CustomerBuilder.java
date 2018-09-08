package com.estate.Builder;

public class CustomerBuilder {
    private String name;
    private String email;
    private String phoneNumber;
    public CustomerBuilder(Builder builder){
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder{
        private String name;
        private String email;
        private String phoneNumber;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public CustomerBuilder build(){
            return new CustomerBuilder(this);
        }
    }
}
