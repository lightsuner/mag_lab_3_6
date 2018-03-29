package com.zerg.unit;

import com.gameapi.Unit;
import com.zerg.etc.RaceInfo;

public abstract class AbstractZergUnit implements Unit {
    protected int mLife;
    protected int mEnergy;
    protected int mArmor;
    protected int mSupply;
    protected int mSight;
    protected int mX;
    protected int mY;
    protected int mWidth;
    protected int mHeight;

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
