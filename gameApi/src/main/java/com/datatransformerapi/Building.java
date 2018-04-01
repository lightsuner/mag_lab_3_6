package com.datatransformerapi;

public interface Building extends GameObject {

    int getLife();

    void setLife(int life);

    int getShield();

    void setShield(int shield);

    ResourceCost getCost();
}
