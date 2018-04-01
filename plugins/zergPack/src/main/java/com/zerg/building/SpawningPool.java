package com.zerg.building;

import com.datatransformerapi.DefaultResourceCost;

public class SpawningPool extends AbstractZergBuilding {
    static final String UNIT_NAME = "Spawning Pool";

    public SpawningPool() {
        mLife = 1000;
        mShield = 1;
        mCost = new DefaultResourceCost(200);
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
