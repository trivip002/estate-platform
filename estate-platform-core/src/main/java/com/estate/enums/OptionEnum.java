package com.estate.enums;

public enum OptionEnum {
    rentBase("Thuê nguyên căn"),
    groundFloor("Tầng trệt"),
    furniture("Nội thất");

    private final String value;

    public String getValue(){
        return this.value;
    }
    OptionEnum(String value) {
        this.value = value;
    }
}
