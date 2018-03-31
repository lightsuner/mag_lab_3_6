package com.bsuir.lab36.view;

import com.gameapi.GameObject;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Callback;

public class ActionsCellFactory implements Callback<TableColumn, TableCell> {

    private Callback<GameObject, Boolean> mOnEditCallback;
    private Callback<GameObject, Boolean> mOnDeleteCallback;

    public ActionsCellFactory() {
    }

    public ActionsCellFactory(Callback<GameObject, Boolean> onEditCallback, Callback<GameObject, Boolean> onDeleteCallback) {
        mOnEditCallback = onEditCallback;
        mOnDeleteCallback = onDeleteCallback;
    }

    @Override
    public TableCell call(TableColumn p) {
        TableCell<Object, GameObject> cell = new TableCell<Object, GameObject>() {
            private HBox mContainer;
            private ImageView mEditImageView;
            private ImageView mDeleteImageView;

            {
                mEditImageView = new ImageView();
                mDeleteImageView = new ImageView();

                initImageView(mEditImageView, "edit.png");
                initImageView(mDeleteImageView, "delete.png");

                Region region1 = new Region();
                HBox.setHgrow(region1, Priority.ALWAYS);

                mContainer = new HBox(mEditImageView, region1, mDeleteImageView);
                setGraphic(mContainer);
            }

            @Override
            protected void updateItem(GameObject item, boolean empty) {

                // calling super here is very important - don't skip this!
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    mEditImageView.setOnMouseClicked(e -> {
                        onEditClick(item);
                    });
                    mDeleteImageView.setOnMouseClicked(e -> {
                        onDeleteClick(item);
                    });
                    setGraphic(mContainer);
                }
            }
        };


        return cell;
    }

    private void initImageView(ImageView view, String imagePath) {
        view.setFitHeight(16);
        view.setFitWidth(16);
        view.setVisible(true);
        view.setCache(true);
        view.setImage(new Image(getClass().getClassLoader().getResourceAsStream(imagePath)));
        view.setStyle("-fx-cursor: hand");
    }

    private void onEditClick(GameObject item) {
        System.out.println("onEditClick: " + item);
        if (mOnEditCallback != null) {
            mOnEditCallback.call(item);
        }
    }

    private void onDeleteClick(GameObject item) {
        System.out.println("onDeleteClick: " + item);
        if (mOnDeleteCallback != null) {
            mOnDeleteCallback.call(item);
        }
    }
}