package com.gameapi;

import com.gameapi.exception.FactoryFailedException;

public interface UnitFactory {
    Unit createUnit(String type) throws FactoryFailedException;
}
