package com.terran.building;

import com.gameapi.DefaultResourceCost;

public class Starport extends AbstractTerranBuilding {
    static final String UNIT_NAME = "Starport";

    public Starport() {
        mLife = 1300;
        mShield = 1;
        mCost = new DefaultResourceCost(150, 100);
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
