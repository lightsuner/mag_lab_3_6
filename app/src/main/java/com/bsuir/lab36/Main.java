package com.bsuir.lab36;

import com.gameapi.GameObjectsPack;
import javafx.stage.Stage;
import org.pf4j.*;

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
        PluginManager pluginManager = new DefaultPluginManager();

        // start and load all plugins of application
        pluginManager.loadPlugins();
        pluginManager.startPlugins();

        // retrieve all extensions for "Greeting" extension point
        List<GameObjectsPack> gameObjectPacks = pluginManager.getExtensions(GameObjectsPack.class);
        System.out.println("Plugins count: " + gameObjectPacks.size());
        for (GameObjectsPack gamePack : gameObjectPacks) {
            System.out.println(">>> " + gamePack.getPackId() + ":\tBuildings: " + String
                .join(", ", gamePack.getBuildingsList()) + "\t Units: " + String.join(", ", gamePack.getUnitsList()));
        }

        // stop and unload all plugins
        pluginManager.stopPlugins();
    }
}
