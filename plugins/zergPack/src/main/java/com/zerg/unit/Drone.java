package com.zerg.unit;

import com.gameapi.DefaultResourceCost;

public class Drone extends AbstractZergBuildableUnit {
    static final String UNIT_NAME = "Drone";

    public Drone() {
        mLife = 40;
        mArmor = 0;
        mBuildFrom = Larva.UNIT_NAME;
        mBuildTime = 17;
        mCost = new DefaultResourceCost(50);
        mSupply = 1;
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
