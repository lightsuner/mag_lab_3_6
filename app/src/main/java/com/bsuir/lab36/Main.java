package com.bsuir.lab36;

import com.bsuir.lab36.controller.MainScreenController;
import com.bsuir.lab36.utils.JoinClassLoader;
import com.datatransformerapi.DataProcessorFactory;
import com.datatransformerapi.FormatDataTransformerFactory;
import com.datatransformerapi.GameObjectsPack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pf4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static PluginManager mPluginManager;
    private Parent mRootLayout;

    @Override
    public void start(Stage primaryStage) {
        try {
            loadUI();
            loadPlugins();
            Scene scene = new Scene(mRootLayout, 1200, 700);
            primaryStage.setTitle("BSUIR LAB 3 - 6!");
            primaryStage.setScene(scene);
            primaryStage.show();


            MainScreenController viewController = new MainScreenController();
            viewController.setGameObjectPacks(mPluginManager.getExtensions(GameObjectsPack.class))
                          .setFormatDataTransformerFactories(mPluginManager
                              .getExtensions(FormatDataTransformerFactory.class))
                          .setDataProcessorFactories(mPluginManager.getExtensions(DataProcessorFactory.class))
                          .init(mRootLayout);

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


    private void loadPlugins() {
        // create the plugin manager
        mPluginManager = new DefaultPluginManager();

        // start and load all plugins of application
        mPluginManager.loadPlugins();
        mPluginManager.startPlugins();

        List<ClassLoader> classLoadersList = new ArrayList<>();

        mPluginManager.getPlugins().forEach(pluginWrapper -> {
            classLoadersList.add(pluginWrapper.getPluginClassLoader());
        });


        ClassLoader[] classLoadersArray = new ClassLoader[classLoadersList.size()];
        classLoadersArray = classLoadersList.toArray(classLoadersArray);

        if (!classLoadersList.isEmpty()) {
            ClassLoader mainClassLoader = new JoinClassLoader(Thread.currentThread()
                                                                    .getContextClassLoader(), classLoadersArray);
            Thread.currentThread().setContextClassLoader(mainClassLoader);
        }
    }
}
