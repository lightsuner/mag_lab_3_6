package com.bsuir.lab36.controller;

import com.bsuir.lab36.presenter.GameObjectEditPresenter;
import com.bsuir.lab36.presenter.GameObjectTablePresenter;
import com.bsuir.lab36.presenter.PersistentControlsPresenter;
import com.bsuir.lab36.presenter.UnitPackControlsPresenter;
import com.datatransformerapi.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.List;

public class MainScreenController {
    private Pane mMenuPane;
    private TableView<GameObject> mTableView;

    private List<GameObjectsPack> mGameObjectPacks;
    private ObservableList<GameObject> mGameObjects;
    private ScrollPane mEditContainer;
    private Pane mPersistenceActionContainer;
    private List<FormatDataTransformerFactory> mFormatDataTransformerFactories;
    private List<DataProcessorFactory> mDataProcessorFactories;

    public MainScreenController() {
    }

    public MainScreenController setGameObjectPacks(List<GameObjectsPack> gameObjectPacks) {
        mGameObjectPacks = gameObjectPacks;

        return this;
    }

    public void init(Parent rootLayout) {
        mGameObjects = FXCollections.observableArrayList();
        bindViews(rootLayout);
        createTablePresenter();
        createGamePackUI();
        createGamePackUI();
        createPersistentControls();

        mGameObjects.addListener((ListChangeListener<GameObject>) c -> System.out.println(c));
    }

    private void bindViews(Parent rootLayout) {
        mMenuPane = (Pane) rootLayout.getScene().lookup("#menuPane");
        mTableView = (TableView<GameObject>) rootLayout.getScene().lookup("#tableView");
        mEditContainer = (ScrollPane) rootLayout.getScene().lookup("#editContainer");
        mPersistenceActionContainer = (Pane) rootLayout.getScene().lookup("#persistenceActionView");
    }

    private void createGamePackUI() {
        mMenuPane.getChildren().clear();
        if (mGameObjectPacks != null) {
            mGameObjectPacks.forEach(this::addObjectPackView);
        }
    }

    private void createTablePresenter() {
        GameObjectTablePresenter tablePresenter = new GameObjectTablePresenter(mTableView, mGameObjects);
        tablePresenter.setOnEditListener(this::showEditItemView);
    }

    private void addObjectPackView(GameObjectsPack pack) {
        UnitPackControlsPresenter viewBuilder = new UnitPackControlsPresenter(pack);

        viewBuilder.setOnNewObjectListener(this::addNewGameObject);

        Pane pane = viewBuilder.buildUI();
        pane.prefWidthProperty().bind(((Pane) mMenuPane.getParent()).widthProperty());

        mMenuPane.getChildren().add(pane);
    }

    private void showEditItemView(GameObject item) {
        GameObjectEditPresenter editPresenter = new GameObjectEditPresenter(item);

        editPresenter.setOnSaveListener(gameObjectItem -> {

            mEditContainer.setContent(null);
            fireListUpdateOnItemChange(gameObjectItem);
        });

        mEditContainer.setContent(editPresenter.buildUI());
    }

    private void createPersistentControls() {
        PersistentControlsPresenter presenter = new PersistentControlsPresenter(mGameObjects, mFormatDataTransformerFactories, mDataProcessorFactories);
        Pane pane = presenter.buildUI();
        pane.prefWidthProperty().bind(mPersistenceActionContainer.widthProperty());
        pane.prefHeightProperty().bind(mPersistenceActionContainer.heightProperty());

        mPersistenceActionContainer.getChildren().addAll(pane);
    }

    private void fireListUpdateOnItemChange(GameObject item) {
        int index = mGameObjects.indexOf(item);
        if (index == -1) {
            return;
        }
        mGameObjects.set(index, item);
    }

    private void addNewGameObject(GameObject item) {
        mGameObjects.add(item);
    }

    public MainScreenController setFormatDataTransformerFactories(List<FormatDataTransformerFactory> factories) {
        mFormatDataTransformerFactories = factories;

        return this;
    }

    public MainScreenController setDataProcessorFactories(List<DataProcessorFactory> factories) {
        mDataProcessorFactories = factories;

        return this;
    }
}
