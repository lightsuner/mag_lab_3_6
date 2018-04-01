package com.zerg.building;

import com.datatransformerapi.Building;
import com.datatransformerapi.BuildingFactory;
import com.datatransformerapi.exception.FactoryFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZergBuildingFactory implements BuildingFactory {
    final private static Map<String, Class<? extends AbstractZergBuilding>> mBuildings;

    static {
        mBuildings = new HashMap();
        mBuildings.put(Hatchery.UNIT_NAME, Hatchery.class);
        mBuildings.put(Extractor.UNIT_NAME, Extractor.class);
        mBuildings.put(SpawningPool.UNIT_NAME, SpawningPool.class);
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
