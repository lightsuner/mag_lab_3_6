package com.terran.building;

import com.gameapi.DefaultResourceCost;

public class CommandCenter extends AbstractTerranBuilding {
    static final String UNIT_NAME = "Command Center";

    public CommandCenter() {
        mLife = 1500;
        mShield = 1;
        mCost = new DefaultResourceCost(400);
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
