package com.castis.adlib.dto.enumeration;

public enum Status {
    UNKNOWN("UNKNOWN"),
    INIT("INIT"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private final String value;

    private Status(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static Status valueof(String value) {
        Status[] list = Status.values();
        for (Status type : list) {
            if (type.toString().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return Status.UNKNOWN;
    }


}
