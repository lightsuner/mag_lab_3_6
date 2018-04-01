package com.bsuir.lab36.presenter;

import com.bsuir.lab36.utils.Callback;
import com.datatransformerapi.GameObject;
import com.datatransformerapi.GameObjectsPack;
import com.datatransformerapi.exception.FactoryFailedException;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UnitPackControlsPresenter {
    private GameObjectsPack mGameObjectsPack;
    private VBox mRootContainer;
    private Callback<GameObject> mOnNewObjectListener;

    public UnitPackControlsPresenter(GameObjectsPack pack) {
        mGameObjectsPack = pack;
    }

    public Pane buildUI() {
        mRootContainer = new VBox();
        if (isPackEmpty()) {
            return mRootContainer;
        }

        mRootContainer.setStyle("-fx-border-width: 1; -fx-border-color: black");
        mRootContainer.setPadding(new Insets(15));
        mRootContainer.setSpacing(10);
        AnchorPane.setLeftAnchor(mRootContainer, 0.0);
        AnchorPane.setRightAnchor(mRootContainer, 0.0);
        mRootContainer.setFillWidth(true);

        String packLabelText = StringUtils.capitalize(StringUtils
            .join(StringUtils.splitByCharacterTypeCamelCase(mGameObjectsPack.getPackId()), StringUtils.SPACE));

        Label packLabel = new Label(packLabelText);

        mRootContainer.getChildren().add(packLabel);
        mRootContainer.getChildren().add(new Separator());

        buildBuildingsControl();
        buildUnitsControl();

        return mRootContainer;
    }

    public void setOnNewObjectListener(Callback<GameObject> onNewObjectListener) {
        mOnNewObjectListener = onNewObjectListener;
    }

    private void fireObjectCreate(GameObject object) {
        if (mOnNewObjectListener == null) {
            return;
        }

        mOnNewObjectListener.call(object);
    }

    private boolean isPackEmpty() {
        return mGameObjectsPack.getUnitsList().isEmpty() && mGameObjectsPack.getBuildingsList().isEmpty();
    }

    private void createSubControl(String label, List<String> itemList, javafx.util.Callback<String, GameObject> factory) {
        mRootContainer.getChildren().add(new Label(label));

        FlowPane bntContainer = new FlowPane();
        bntContainer.setHgap(10);
        bntContainer.setVgap(20);
        bntContainer.setPadding(new Insets(5, 0, 5, 0));

        mRootContainer.getChildren().add(bntContainer);

        itemList.forEach(type -> {
            Button button = new Button(type);
            bntContainer.getChildren().add(button);
            button.setOnMouseClicked(event -> fireObjectCreate(factory.call(type)));
        });
    }

    private void buildBuildingsControl() {
        if (mGameObjectsPack.getBuildingsList().isEmpty()) {
            return;
        }

        createSubControl("Buildings", mGameObjectsPack.getBuildingsList(), type -> {
            try {
                return mGameObjectsPack.getBuildingFactory().createBuilding(type);
            } catch (FactoryFailedException e) {
                e.printStackTrace();
            }

            return null;
        });
    }

    private void buildUnitsControl() {
        if (mGameObjectsPack.getUnitsList().isEmpty()) {
            return;
        }

        createSubControl("Units", mGameObjectsPack.getUnitsList(), type -> {
            try {
                return mGameObjectsPack.getUnitFactory().createUnit(type);
            } catch (FactoryFailedException e) {
                e.printStackTrace();
            }

            return null;
        });
    }
}
