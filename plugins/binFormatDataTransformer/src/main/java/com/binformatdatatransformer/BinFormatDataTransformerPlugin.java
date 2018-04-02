package com.binformatdatatransformer;

import com.datatransformerapi.FormatDataTransformer;
import com.datatransformerapi.FormatDataTransformerFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class BinFormatDataTransformerPlugin extends Plugin {

    public BinFormatDataTransformerPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class BinFormatDataTransformerFactory implements FormatDataTransformerFactory {
        @Override
        public String getType() {
            return "bin";
        }

        @Override
        public FormatDataTransformer create() {
            return new BinFormatDataTransformer();
        }
    }
}
