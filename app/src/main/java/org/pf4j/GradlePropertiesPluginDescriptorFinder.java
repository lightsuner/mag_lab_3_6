package org.pf4j;

import org.pf4j.util.StringUtils;

import java.util.Properties;

public class GradlePropertiesPluginDescriptorFinder extends PropertiesPluginDescriptorFinder {
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "gradle.properties";

    public GradlePropertiesPluginDescriptorFinder() {
        super(DEFAULT_PROPERTIES_FILE_NAME);
    }

    public GradlePropertiesPluginDescriptorFinder(String propertiesFileName) {
        super(propertiesFileName);
    }

    @Override
    protected PluginDescriptor createPluginDescriptor(Properties properties) {
        DefaultPluginDescriptor pluginDescriptor = createPluginDescriptorInstance();

        // TODO validate !!!
        String id = properties.getProperty("pluginId");
        pluginDescriptor.setPluginId(id);

        String description = properties.getProperty("pluginDescription");
        if (StringUtils.isNullOrEmpty(description)) {
            pluginDescriptor.setPluginDescription("");
        } else {
            pluginDescriptor.setPluginDescription(description);
        }

        String clazz = properties.getProperty("pluginClass");
        pluginDescriptor.setPluginClass(clazz);

        String version = properties.getProperty("version");
        if (StringUtils.isNotNullOrEmpty(version)) {
            pluginDescriptor.setPluginVersion(version);
        }

        String provider = properties.getProperty("pluginProvider");
        pluginDescriptor.setProvider(provider);

        String dependencies = properties.getProperty("pluginDependencies");
        pluginDescriptor.setDependencies(dependencies);

        String requires = properties.getProperty("pluginRequires");
        if (StringUtils.isNotNullOrEmpty(requires)) {
            pluginDescriptor.setRequires(requires);
        }

        pluginDescriptor.setLicense(properties.getProperty("pluginLicense"));

        return pluginDescriptor;
    }
}
