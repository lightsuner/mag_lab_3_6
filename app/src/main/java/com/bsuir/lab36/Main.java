package com.bsuir.lab36;

import org.pf4j.GradleDevelopmentPluginClasspath;
import org.pf4j.GradlePropertiesPluginDescriptorFinder;
import com.gameapi.GameObjectsPack;
import javafx.stage.Stage;
import org.pf4j.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//public class Main extends Application {
public class Main {
    public static void main(String[] args) {
        testPlugins();
        //launch(args);
    }

    //@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BSUIR LAB 3 - 6!");
        primaryStage.show();
    }

    private static void testPlugins() {
        // create the plugin manager
        PluginManager pluginManager = new DefaultPluginManager() {

            @Override
            protected PluginDescriptorFinder createPluginDescriptorFinder() {
                return new CompoundPluginDescriptorFinder()
                    //.add(new GradlePropertiesPluginDescriptorFinder())
                    .add(new PropertiesPluginDescriptorFinder())
                    .add(new ManifestPluginDescriptorFinder());
            }
        };

        // start and load all plugins of application
        pluginManager.loadPlugins();
        pluginManager.startPlugins();

        // retrieve all extensions for "Greeting" extension point
        List<GameObjectsPack> packs = pluginManager.getExtensions(GameObjectsPack.class);
        System.out.println("Plugins count: " + packs.size());
        for (GameObjectsPack pack : packs) {
            System.out.println(">>> " + pack.getPackId());
        }

        // stop and unload all plugins
        pluginManager.stopPlugins();
    }
}
