package com.zipdataprocessor;

import com.datatransformerapi.*;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class ZipDataProcessorPlugin extends Plugin {

    public ZipDataProcessorPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class ZipDataProcessorFactory implements DataProcessorFactory {
        @Override
        public String getType() {
            return "zip";
        }

        @Override
        public DataProcessor create(DataTransformer dataTransformer) {
            return new ZipDataProcessor(dataTransformer);
        }
    }
}
