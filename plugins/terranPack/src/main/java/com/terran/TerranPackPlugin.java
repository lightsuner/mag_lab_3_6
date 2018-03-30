package com.terran;

import com.gameapi.BuildingFactory;
import com.gameapi.GameObjectsPack;
import com.gameapi.UnitFactory;

import com.terran.building.TerranBuildingFactory;
import com.terran.unit.TerranUnitFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.util.List;

public class TerranPackPlugin extends Plugin {

    public TerranPackPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class TerranGameObjectsPack implements GameObjectsPack {
        private final TerranUnitFactory mUnitFactory;
        private final TerranBuildingFactory mBuildingFactory;

        public TerranGameObjectsPack() {
            mUnitFactory = new TerranUnitFactory();
            mBuildingFactory = new TerranBuildingFactory();
        }

        @Override
        public String getPackId() {
            return "terranPack";
        }

        @Override
        public List<String> getUnitsList() {
            return mUnitFactory.getUnitsList();
        }

        @Override
        public UnitFactory getUnitFactory() {
            return mUnitFactory;
        }

        @Override
        public List<String> getBuildingsList() {
            return mBuildingFactory.getBuildingsList();
        }

        @Override
        public BuildingFactory getBuildingFactory() {
            return mBuildingFactory;
        }
    }
}
