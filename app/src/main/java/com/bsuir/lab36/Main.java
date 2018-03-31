package com.bsuir.lab36;

import com.bsuir.lab36.presenter.MainScreenPresenter;
import com.gameapi.GameObjectsPack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pf4j.*;

import java.io.IOException;

public class Main extends Application {
    private static PluginManager mPluginManager;
    private Parent mRootLayout;
    private MainScreenPresenter mViewPresenter;
    //public class Main {
/*    public static void main(String[] args) {
        testPlugins();
        //launch(args);
    }*/

    //@Override
    public void start(Stage primaryStage) {
        try {
            loadUI();
            loadPlugins();
            Scene scene = new Scene(mRootLayout, 1200, 700);
            primaryStage.setTitle("BSUIR LAB 3 - 6!");
            primaryStage.setScene(scene);
            primaryStage.show();

            mViewPresenter = new MainScreenPresenter();
            mViewPresenter.setGameObjectPacks(mPluginManager.getExtensions(GameObjectsPack.class)).init(mRootLayout);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (mPluginManager != null) {
            mPluginManager.stopPlugins();
            mPluginManager = null;
        }
    }

    private void loadUI() throws IOException {
        mRootLayout = FXMLLoader.load(getClass().getResource("/main.fxml"));
    }


    private static void loadPlugins() {
        // create the plugin manager
        mPluginManager = new DefaultPluginManager();

        // start and load all plugins of application
        mPluginManager.loadPlugins();
        mPluginManager.startPlugins();

/*        // retrieve all extensions for "Greeting" extension point
        List<GameObjectsPack> gameObjectPacks = pluginManager.getExtensions(GameObjectsPack.class);
        System.out.println("Plugins count: " + gameObjectPacks.size());
        for (GameObjectsPack gamePack : gameObjectPacks) {
            System.out.println(">>> " + gamePack.getPackId() + ":\tBuildings: " + String
                .join(", ", gamePack.getBuildingsList()) + "\t Units: " + String.join(", ", gamePack.getUnitsList()));
        }

        // stop and unload all plugins
        pluginManager.stopPlugins();*/
    }
}
