package org.thomcgn.utils;

public enum DaysOfWeek {
    MONDAY("Montag"),
    TUESDAY("Dienstag"),
    WEDNESDAY("Mittwoch"),
    THURSDAY("Donnerstag"),
    FRIDAY("Freitag"),
    SATURDAY("Samstag"),
    SUNDAY("Sonntag");

    private final String value;

    DaysOfWeek(String value) {
        this.value = value;

    }

    public String getValue() {
        return switch (this) {
            case SATURDAY, SUNDAY -> "Wochenende";
            default -> value;
        };
    }
}
