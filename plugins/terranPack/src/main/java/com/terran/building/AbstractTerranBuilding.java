package com.terran.building;

import com.datatransformerapi.Building;
import com.datatransformerapi.ResourceCost;
import com.terran.etc.RaceInfo;

public abstract class AbstractTerranBuilding implements Building {

    protected int mLife;
    protected int mShield;

    protected ResourceCost mCost;

    protected int mX;
    protected int mY;
    protected int mWidth;
    protected int mHeight;

    @Override
    public int getLife() {
        return mLife;
    }

    @Override
    public void setLife(int life) {
        mLife = life;
    }

    @Override
    public int getShield() {
        return mShield;
    }

    @Override
    public void setShield(int shield) {
        mShield = shield;
    }

    @Override
    public ResourceCost getCost() {
        return mCost;
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
