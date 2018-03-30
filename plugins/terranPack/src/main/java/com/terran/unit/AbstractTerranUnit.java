package com.terran.unit;

import com.gameapi.BuildableUnit;
import com.gameapi.ResourceCost;
import com.gameapi.Unit;
import com.terran.etc.RaceInfo;

public abstract class AbstractTerranUnit implements Unit, BuildableUnit {
    protected int mLife;
    protected int mEnergy;
    protected int mArmor;
    protected int mSupply;
    protected int mSight;
    protected int mX;
    protected int mY;
    protected int mWidth;
    protected int mHeight;

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

    @Override
    public boolean isFlying() {
        return false;
    }

    @Override
    public int getLife() {
        return mLife;
    }

    @Override
    public void setLife(int life) {
        mLife = life;
    }

    @Override
    public int getEnergy() {
        return mEnergy;
    }

    @Override
    public void setEnergy(int energy) {
        mEnergy = energy;
    }

    @Override
    public int getArmor() {
        return mArmor;
    }

    @Override
    public void setArmor(int armor) {
        mArmor = armor;
    }

    @Override
    public int getSupply() {
        return mSupply;
    }

    @Override
    public void setSupply(int supply) {
        mSupply = supply;
    }

    @Override
    public int getSight() {
        return mSight;
    }

    @Override
    public void setSight(int sight) {
        mSight = sight;
    }

    @Override
    public String getRace() {
        return RaceInfo.NAME;
    }

    @Override
    public int getX() {
        return mX;
    }

    @Override
    public void setX(int x) {
        mX = x;
    }

    @Override
    public int getY() {
        return mY;
    }

    @Override
    public void setY(int y) {
        mY = y;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public void setWidth(int width) {
        mWidth = width;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public void setHeight(int height) {
        mHeight = height;
    }
}
