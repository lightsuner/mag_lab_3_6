package com.zerg.unit;

import com.gameapi.BuildableUnit;
import com.gameapi.ResourceCost;


public abstract class AbstractZergBuildableUnit extends AbstractZergUnit implements BuildableUnit {
    protected int mBuildTime;
    protected String mBuildFrom;
    protected ResourceCost mCost;

    @Override
    public int getBuildTime() {
        return mBuildTime;
    }

    @Override
    public void setBuildTime(int buildTime) {
        mBuildTime = buildTime;
    }

    @Override
    public String getBuildFrom() {
        return mBuildFrom;
    }

    @Override
    public void setBuildFrom(String buildFrom) {
        mBuildFrom = buildFrom;
    }

    @Override
    public ResourceCost getCost() {
        return mCost;
    }
}
