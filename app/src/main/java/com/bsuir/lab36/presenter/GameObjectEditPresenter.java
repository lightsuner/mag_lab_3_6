package com.bsuir.lab36.presenter;

import com.bsuir.lab36.helpers.UIHelper;
import com.bsuir.lab36.utils.Callback;
import com.gameapi.GameObject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameObjectEditPresenter {
    private final UIHelper mUIHelper;
    private GameObject mGameObject;
    private Callback<GameObject> mOnSaveListener;

    public GameObjectEditPresenter(GameObject gameObject) {
        mGameObject = gameObject;
        mUIHelper = new UIHelper();
    }

    public Node buildUI() {
        VBox node = mUIHelper.createVBoxContainer(mGameObject);

        Button button = new Button("Save");

        node.getChildren().add(button);

        button.setOnMouseClicked(event -> {
            fireUpdateItem();
        });


        return node;
    }

    public void setOnSaveListener(Callback<GameObject> onSaveListener) {
        mOnSaveListener = onSaveListener;
    }

    private void fireUpdateItem() {
        if (mOnSaveListener == null) {
            return;
        }
        mOnSaveListener.call(mGameObject);
    }
}
