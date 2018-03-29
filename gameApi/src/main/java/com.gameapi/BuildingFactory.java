package com.gameapi;

import com.gameapi.exception.FactoryFailedException;

public interface BuildingFactory {
    Building createBuilding(String type) throws FactoryFailedException;
}
