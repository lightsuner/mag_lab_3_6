package com.datatransformerapi;

import com.datatransformerapi.exception.FactoryFailedException;

public interface UnitFactory {
    Unit createUnit(String type) throws FactoryFailedException;
}
