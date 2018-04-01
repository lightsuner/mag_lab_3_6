package com.datatransformerapi;

import org.pf4j.ExtensionPoint;

public interface FormatDataTransformerFactory extends ExtensionPoint {
    String getType();

    FormatDataTransformer create();
}
