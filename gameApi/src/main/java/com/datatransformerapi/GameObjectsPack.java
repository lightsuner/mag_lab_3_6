package com.datatransformerapi;

import org.pf4j.ExtensionPoint;

import java.util.List;

public interface GameObjectsPack extends ExtensionPoint {

    String getPackId();

    List<String> getUnitsList();

    UnitFactory getUnitFactory();

    List<String> getBuildingsList();

    BuildingFactory getBuildingFactory();
}
