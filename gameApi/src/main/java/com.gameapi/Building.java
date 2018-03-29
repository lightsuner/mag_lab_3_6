package com.gameapi;

public interface Building extends GameObject {

    int getLife();

    void setLife(int life);

    int getShield();

    void setShield(int shield);

    ResourceCost getCost();

    void setCost(ResourceCost cost);
}
