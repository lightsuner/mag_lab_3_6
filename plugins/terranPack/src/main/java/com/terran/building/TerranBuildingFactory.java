package com.terran.building;

import com.datatransformerapi.Building;
import com.datatransformerapi.BuildingFactory;
import com.datatransformerapi.exception.FactoryFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerranBuildingFactory implements BuildingFactory {
    final private static Map<String, Class<? extends AbstractTerranBuilding>> mBuildings;

    static {
        mBuildings = new HashMap();
        mBuildings.put(Barracks.UNIT_NAME, Barracks.class);
        mBuildings.put(CommandCenter.UNIT_NAME, CommandCenter.class);
        mBuildings.put(Starport.UNIT_NAME, Starport.class);
    }

    @Override
    public Building createBuilding(String type) throws FactoryFailedException {
        if (!mBuildings.containsKey(type)) {
            throw new RuntimeException("There is no such building in package: " + type);
        }
        try {
            return mBuildings.get(type).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new FactoryFailedException(e);
        }
    }

    public List<String> getBuildingsList() {
        return new ArrayList<>(mBuildings.keySet());
    }
}
