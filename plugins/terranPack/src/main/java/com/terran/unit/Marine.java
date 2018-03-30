package com.terran.unit;

import com.gameapi.DefaultResourceCost;

public class Marine extends AbstractTerranUnit {
    static final String UNIT_NAME = "Marine";

    public Marine() {
        mLife = 45;
        mBuildFrom = "Barracks";
        mBuildTime = 25;
        mCost = new DefaultResourceCost(50);
        mSupply = 1;
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
