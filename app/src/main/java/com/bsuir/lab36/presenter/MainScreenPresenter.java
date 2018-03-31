package com.bsuir.lab36.presenter;

import com.gameapi.GameObject;
import com.gameapi.GameObjectsPack;
import com.gameapi.exception.FactoryFailedException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainScreenPresenter {
    private Pane mMenuPane;
    private TableView<GameObject> mTableView;

    private List<GameObjectsPack> mGameObjectPacks;
    private ObservableList<GameObject> mGameObjects;

    public MainScreenPresenter() {
    }

    public MainScreenPresenter setGameObjectPacks(List<GameObjectsPack> gameObjectPacks) {
        mGameObjectPacks = gameObjectPacks;

        return this;
    }

    public void init(Parent rootLayout) {
        mGameObjects = FXCollections.observableArrayList();
        bindViews(rootLayout);
        createGamePackUI();

        mGameObjects.addListener((ListChangeListener<GameObject>) c -> System.out.println(c));
    }

    private void createGamePackUI() {
        mMenuPane.getChildren().clear();
        mGameObjectPacks.forEach(pack -> {
            mMenuPane.getChildren().add(createObjectPackView(pack));
        });

    }

    private void bindViews(Parent rootLayout) {
        mMenuPane = (Pane) rootLayout.getScene().lookup("#menuPane");
        mTableView = (TableView<GameObject>) rootLayout.getScene().lookup("#tableView");

        new GameObjectTablePresenter(mTableView, mGameObjects);
    }

    private Node createObjectPackView(GameObjectsPack pack) {
        VBox rootPane = new VBox();
        rootPane.setStyle("-fx-border-width: 1; -fx-border-color: black");

        Label packLabel = new Label(pack.getPackId());

        rootPane.getChildren().add(packLabel);
        rootPane.getChildren().add(new Separator());


        if (!pack.getBuildingsList().isEmpty()) {
            rootPane.getChildren().add(new Label("Buildings"));
            GridPane bntContainer = new GridPane();
            rootPane.getChildren().add(bntContainer);
            pack.getBuildingsList().forEach(buildingType -> {
                Button button = new Button(buildingType);
                button.setOnMouseClicked(event -> {
                    try {
                        mGameObjects.add(pack.getBuildingFactory().createBuilding(buildingType));
                    } catch (FactoryFailedException e) {
                        e.printStackTrace();
                    }
                });
                bntContainer.getChildren().add(button);
            });
        }


        if (!pack.getUnitsList().isEmpty()) {
            rootPane.getChildren().add(new Label("Units"));
            GridPane bntContainer = new GridPane();
            rootPane.getChildren().add(bntContainer);
            pack.getUnitsList().forEach(unitType -> {
                Button button = new Button(unitType);
                button.setOnMouseClicked(event -> {
                    try {
                        mGameObjects.add(pack.getUnitFactory().createUnit(unitType));
                    } catch (FactoryFailedException e) {
                        e.printStackTrace();
                    }
                });
                bntContainer.getChildren().add(button);
            });
        }

        return rootPane;
    }
}
