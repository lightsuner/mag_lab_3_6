package com.zerg.building;

import com.gameapi.DefaultResourceCost;

public class Extractor extends AbstractZergBuilding {
    static final String UNIT_NAME = "Extractor";

    public Extractor() {
        mLife = 500;
        mShield = 1;
        mCost = new DefaultResourceCost(25);
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
