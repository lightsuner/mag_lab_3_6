package com.zerg.building;

import com.gameapi.DefaultResourceCost;

public class Hatchery extends AbstractZergBuilding {
    static final String UNIT_NAME = "Hatchery";

    public Hatchery() {
        mLife = 1500;
        mShield = 1;
        mCost = new DefaultResourceCost(300);
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
