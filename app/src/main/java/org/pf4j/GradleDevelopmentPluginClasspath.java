package org.pf4j;

import org.pf4j.DevelopmentPluginClasspath;

public class GradleDevelopmentPluginClasspath extends DevelopmentPluginClasspath {
    public GradleDevelopmentPluginClasspath() {
        super();
        addClassesDirectories("src/main/java");
    }
}
