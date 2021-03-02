package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import java.util.List;

public class CommonDepositModelBase {
    protected String type;
    protected List<String> dimensions;
    protected String name;

    public CommonDepositModelBase(String type, List<String> dimensions, String name) {
        this.type = type;
        this.dimensions = dimensions;
        this.name = name;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
