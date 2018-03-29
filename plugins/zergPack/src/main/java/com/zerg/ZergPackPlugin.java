package com.zerg;

import com.gameapi.BuildingFactory;
import com.gameapi.GameObjectsPack;
import com.gameapi.UnitFactory;
import com.zerg.building.ZergBuildingFactory;
import com.zerg.unit.ZergUnitFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.util.List;

public class ZergPackPlugin extends Plugin {

    public ZergPackPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class ZergGameObjectsPack implements GameObjectsPack {
        private final ZergUnitFactory mUnitFactory;
        private final ZergBuildingFactory mBuildingFactory;

        public ZergGameObjectsPack() {
            mUnitFactory = new ZergUnitFactory();
            mBuildingFactory = new ZergBuildingFactory();
        }

        @Override
        public String getPackId() {
            return "zergPack";
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
