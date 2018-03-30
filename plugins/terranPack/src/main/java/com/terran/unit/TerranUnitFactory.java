package com.terran.unit;

import com.gameapi.Unit;
import com.gameapi.UnitFactory;
import com.gameapi.exception.FactoryFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerranUnitFactory implements UnitFactory {
    final private static Map<String, Class<? extends AbstractTerranUnit>> mUnits;

    static {
        mUnits = new HashMap();
        mUnits.put(SCV.UNIT_NAME, SCV.class);
        mUnits.put(Marine.UNIT_NAME, Marine.class);
        mUnits.put(Battlecruiser.UNIT_NAME, Battlecruiser.class);
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
