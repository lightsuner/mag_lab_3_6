package com.gameapi;

public interface BuildableUnit {
    int getBuildTime();

    void setBuildTime(int buildTime);

    String getBuildFrom();

    void setBuildFrom(String buildFrom);

    ResourceCost getCost();
}
