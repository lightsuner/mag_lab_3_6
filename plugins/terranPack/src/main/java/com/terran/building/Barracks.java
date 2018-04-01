package com.terran.building;

import com.datatransformerapi.DefaultResourceCost;

public class Barracks extends AbstractTerranBuilding {
    static final String UNIT_NAME = "Barracks";

    public Barracks() {
        mLife = 1000;
        mShield = 1;
        mCost = new DefaultResourceCost(150);
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
