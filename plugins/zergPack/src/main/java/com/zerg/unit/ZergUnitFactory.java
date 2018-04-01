package com.zerg.unit;

import com.datatransformerapi.Unit;
import com.datatransformerapi.UnitFactory;
import com.datatransformerapi.exception.FactoryFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZergUnitFactory implements UnitFactory {
    final private static Map<String, Class<? extends AbstractZergUnit>> mUnits;

    static {
        mUnits = new HashMap();
        mUnits.put(Larva.UNIT_NAME, Larva.class);
        mUnits.put(Drone.UNIT_NAME, Drone.class);
        mUnits.put(Overlord.UNIT_NAME, Overlord.class);
    }

    @Override
    public Unit createUnit(String type) throws FactoryFailedException {
        if (!mUnits.containsKey(type)) {
            throw new RuntimeException("There is no such unit in package: " + type);
        }
        try {
            return mUnits.get(type).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new FactoryFailedException(e);
        }
    }

    public List<String> getUnitsList() {
        return new ArrayList<>(mUnits.keySet());
    }
}
