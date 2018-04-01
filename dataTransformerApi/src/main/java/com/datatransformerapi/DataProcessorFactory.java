package com.datatransformerapi;

import org.pf4j.ExtensionPoint;

public interface DataProcessorFactory extends ExtensionPoint {
    String getType();

    DataProcessor create(DataTransformer dataTransformer);
}
