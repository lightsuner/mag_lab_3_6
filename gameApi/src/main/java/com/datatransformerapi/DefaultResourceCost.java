package com.datatransformerapi;

public class DefaultResourceCost implements ResourceCost {
    private int mMinerals;
    private int mVespene;

    public DefaultResourceCost(int minerals) {
        mMinerals = minerals;
    }

    public DefaultResourceCost(int minerals, int vespene) {
        mMinerals = minerals;
        mVespene = vespene;
    }

    @Override
    public int getMinerals() {
        return mMinerals;
    }

    @Override
    public void setMinerals(int minerals) {
        mMinerals = minerals;
    }

    @Override
    public int getVespene() {
        return mVespene;
    }

    @Override
    public void setVespene(int vespene) {
        mVespene = vespene;
    }
}
