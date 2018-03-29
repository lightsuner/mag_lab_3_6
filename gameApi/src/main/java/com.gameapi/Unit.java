package com.gameapi;

public interface Unit extends GameObject {

    boolean isFlying();

    int getLife();

    void setLife(int life);

    int getEnergy();

    void setEnergy(int energy);

    int getArmor();

    void setArmor(int armor);

    int getSupply();

    void setSupply(int supply);

    int getSight();

    void setSight(int sight);
}
