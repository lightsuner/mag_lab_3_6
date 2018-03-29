package com.zerg.unit;

public class Larva extends AbstractZergUnit {
    static final String UNIT_NAME = "Larva";

    public Larva() {
        mLife = 25;
        mArmor = 10;
    }

    @Override
    public String getName() {
        return UNIT_NAME;
    }
}
