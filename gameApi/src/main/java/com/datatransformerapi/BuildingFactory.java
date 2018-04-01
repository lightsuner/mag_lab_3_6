package com.datatransformerapi;

import com.datatransformerapi.exception.FactoryFailedException;

public interface BuildingFactory {
    Building createBuilding(String type) throws FactoryFailedException;
}
