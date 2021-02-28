package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import java.util.List;

public class CommonDepositModelBase {
    protected String type;
    protected List<String> dimensions;

    public CommonDepositModelBase(String type, List<String> dimensions) {
        this.type = type;

        this.dimensions = dimensions;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public String getType() {
        return type;
    }
}
