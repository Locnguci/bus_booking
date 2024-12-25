package org.app.bookingbus.enums;

public enum DistrictEnum {
    HANOI("Hà Nội"),
    DANANG("Đà Nẵng"),
    HCM("Hồ Chí Minh"),
    QUANGNINH("Quảng Ninh"),
    NAMDINH("Nam Định");

    private String value;
    private DistrictEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
