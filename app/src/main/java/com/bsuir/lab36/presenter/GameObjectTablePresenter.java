package com.bsuir.lab36.presenter;

import com.bsuir.lab36.view.ActionsCellFactory;
import com.gameapi.Building;
import com.gameapi.GameObject;
import com.gameapi.Unit;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameObjectTablePresenter {
    private final ObservableList<GameObject> mData;
    private TableView<GameObject> mTableView;

    private final TableColumn<GameObject, String> mRaceColumn = new TableColumn<>("Race");
    private final TableColumn<GameObject, String> mTypeColumn = new TableColumn<>("Type");
    private final TableColumn<GameObject, String> mNameColumn = new TableColumn<>("Name");
    private final TableColumn<GameObject, String> mXPosColumn = new TableColumn<>("X pos");
    private final TableColumn<GameObject, String> mYPosColumn = new TableColumn<>("Y pos");
    private final TableColumn<GameObject, String> mWidthColumn = new TableColumn<>("Width");
    private final TableColumn<GameObject, String> mHeightColumn = new TableColumn<>("Height");
    private final TableColumn mActionsColumn = new TableColumn("Actions");

    public GameObjectTablePresenter(TableView<GameObject> tableView, ObservableList<GameObject> data) {
        mTableView = tableView;
        mData = data;
        mTableView.getColumns().clear();

        mRaceColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
        mTypeColumn.setCellValueFactory(param -> {
            if (param == null || param.getValue() == null) {
                return null;
            }

            if (param.getValue() instanceof Unit) {
                return new ReadOnlyStringWrapper("Unit");
            } else if (param.getValue() instanceof Building) {
                return new ReadOnlyStringWrapper("Building");
            }

            return null;
        });
        mNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mXPosColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        mYPosColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        mWidthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        mHeightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));

        mActionsColumn.setCellValueFactory(param -> {
            TableColumn.CellDataFeatures<GameObject, String> castedParam = (TableColumn.CellDataFeatures<GameObject, String>) param;
            return new ReadOnlyObjectWrapper<>(castedParam.getValue());
        });

        mActionsColumn.setCellFactory(new ActionsCellFactory(this::editItem, this::deleteItem));


        mTableView.getColumns()
                  .addAll(mRaceColumn, mTypeColumn, mNameColumn, mXPosColumn, mYPosColumn, mWidthColumn, mHeightColumn, mActionsColumn);

        mTableView.setItems(mData);
    }


    private boolean deleteItem(GameObject item) {
        mData.remove(item);

        return true;
    }

    private boolean editItem(GameObject item) {

        return true;
    }
}