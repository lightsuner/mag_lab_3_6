package com.xmlformatdatatransformer;

import com.datatransformerapi.FormatDataTransformer;
import com.datatransformerapi.FormatDataTransformerFactory;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

public class XMLFormatDataTransformerPlugin extends Plugin {

    public XMLFormatDataTransformerPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class XMLFormatDataTransformerFactory implements FormatDataTransformerFactory {
        @Override
        public String getType() {
            return "xml";
        }

        @Override
        public FormatDataTransformer create() {
            return new XMLFormatDataTransformer();
        }
    }
}
