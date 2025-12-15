package org.thomcgn.utils;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    DIVERSE("Diverse");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
