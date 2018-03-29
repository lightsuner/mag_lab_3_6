package com.zerg;

import com.gameapi.BuildingFactory;
import com.gameapi.GameObjectsPack;
import com.gameapi.UnitFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.util.ArrayList;
import java.util.List;

public class ZergPackPlugin extends Plugin {

    public ZergPackPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class ZergGameObjectsPack implements GameObjectsPack {
        @Override
        public String getPackId() {
            return "zergPack";
        }

        @Override
        public List<String> getUnitsList() {
            return new ArrayList<>();
        }

        @Override
        public UnitFactory getUnitFactory() {
            return null;
        }

        @Override
        public List<String> getBuildingsList() {
            return new ArrayList<>();
        }

        @Override
        public BuildingFactory getBuildingFactory() {
            return null;
        }
    }
}
