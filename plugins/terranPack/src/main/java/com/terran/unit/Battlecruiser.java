package com.terran.unit;

import com.datatransformerapi.DefaultResourceCost;

public class Battlecruiser extends AbstractTerranUnit {
    static final String UNIT_NAME = "Battlecruiser";

    public Battlecruiser() {
        mLife = 550;
        mEnergy = 200;
        mArmor = 3;
        mBuildFrom = "Starport";
        mBuildTime = 90;
        mCost = new DefaultResourceCost(400, 300);
        mSupply = 6;
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }

    @Override
    public boolean isFlying() {
        return true;
    }
}