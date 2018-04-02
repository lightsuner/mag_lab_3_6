package com.datatransformerapi;

import java.io.Serializable;

public interface ResourceCost extends Serializable {

    int getMinerals();

    void setMinerals(int minerals);

    int getVespene();

    void setVespene(int vespene);
}
