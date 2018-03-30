package com.terran.unit;

import com.gameapi.DefaultResourceCost;

public class SCV extends AbstractTerranUnit {
    static final String UNIT_NAME = "SCV";

    public SCV() {
        mLife = 45;
        mBuildFrom = "Command Center";
        mBuildTime = 17;
        mCost = new DefaultResourceCost(50);
        mSupply = 1;
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
