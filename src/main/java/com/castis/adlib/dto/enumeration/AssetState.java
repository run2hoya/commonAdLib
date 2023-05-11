package com.castis.adlib.dto.enumeration;

public enum AssetState {
    UNKNOWN("UNKNOWN"),
    INIT("INIT"),
    DOWNLOAD_COMPLETE("DOWNLOAD_COMPLETE"),
    ENCODING_COMPLETE("ENCODING_COMPLETE"),
    TRANSFER("TRANSFER"),
    TRANSFER_COMPLETE("TRANSFER_COMPLETE"),
    COMPLETE("COMPLETE"),
    FAIL("FAIL");

    private final String value;

    private AssetState(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static AssetState valueof(String value) {
        AssetState[] list = AssetState.values();
        for (AssetState type : list) {
            if (type.toString().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return AssetState.UNKNOWN;
    }


}
