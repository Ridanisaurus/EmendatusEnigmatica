package com.ridanisaurus.emendatusenigmatica.datagen;

public enum PackType {
    DATA("data"),
    RESOURCE("resource")
    ;
    private final String suffix;

    PackType(String suffix) {

        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
