package com.gzipdataprocessor;

import by.bsuir.oop.packer.Packer.GzipPacker;
import com.datatransformerapi.*;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class GZipDataProcessorPlugin extends Plugin {

    public GZipDataProcessorPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class GZipDataProcessorFactory implements DataProcessorFactory {
        @Override
        public String getType() {
            return "gz";
        }

        @Override
        public DataProcessor create(DataTransformer dataTransformer) {
            return new GZipDataProcessorAdapter(dataTransformer, new GzipPacker());
        }
    }
}
