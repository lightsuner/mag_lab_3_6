package com.zerg.unit;

import com.gameapi.DefaultResourceCost;

public class Overlord extends AbstractZergBuildableUnit {
    static final String UNIT_NAME = "Overlord";

    public Overlord() {
        mLife = 200;
        mArmor = 0;
        mBuildFrom = Larva.UNIT_NAME;
        mBuildTime = 25;
        mCost = new DefaultResourceCost(100);
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