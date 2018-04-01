package com.bsuir.lab36.presenter;

import com.datatransformerapi.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class PersistentControlsPresenter {
    private final List<FormatDataTransformerFactory> mFormatDataTransformerFactories;
    private final List<DataProcessorFactory> mDataProcessorFactories;
    private ObservableList<GameObject> mGameObjects;
    private HBox mRootContainer;

    public PersistentControlsPresenter(ObservableList<GameObject> gameObjects, List<FormatDataTransformerFactory> formatFactories, List<DataProcessorFactory> dataProcessorFactories) {
        mGameObjects = gameObjects;
        mFormatDataTransformerFactories = formatFactories;
        mDataProcessorFactories = dataProcessorFactories;
    }

    public Pane buildUI() {
        mRootContainer = new HBox();
        mRootContainer.setMinWidth(100);
        mRootContainer.setMinHeight(100);

        mRootContainer.setStyle("-fx-background-color: aquamarine");
        mRootContainer.setPadding(new Insets(20));

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        Button loadBtn = new Button("Load");
        Button saveBtn = new Button("Save");

        loadBtn.setMinWidth(200);
        saveBtn.setMinWidth(200);
        saveBtn.setOnMouseClicked(this::onSaveClick);

        mRootContainer.getChildren().addAll(loadBtn, region1, saveBtn);

        return mRootContainer;
    }

    private void onSaveClick(Event event) {
        //FileChooser fileChooser = new FileChooser();
        //fileChooser.setTitle("Save data");
        //File file = fileChooser.showSaveDialog(mRootContainer.getScene().getWindow());
/*        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        VBox vbox = new VBox(new Text("Please select"), new Button("Ok."));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();*/
        showSavePreference();
    }

    private void showSavePreference() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Saving preference");
        alert.setHeaderText(null);

        VBox dialogPaneContent = new VBox();
        dialogPaneContent.setSpacing(5);
        Label label = new Label("Please select saving options");

        dialogPaneContent.getChildren().add(label);
        dialogPaneContent.getChildren().add(new Separator());

        Label labelFormat = new Label("Select format");

        dialogPaneContent.getChildren().add(labelFormat);
        dialogPaneContent.getChildren().add(new Separator());

        ChoiceBox<String> formatCB = new ChoiceBox<>();

        mFormatDataTransformerFactories.forEach(factory -> {
            formatCB.getItems().add(factory.getType());
        });

        formatCB.getSelectionModel().selectFirst();

        dialogPaneContent.getChildren().add(formatCB);

        Label labelProcessing = new Label("Select data processor");

        dialogPaneContent.getChildren().add(labelProcessing);
        dialogPaneContent.getChildren().add(new Separator());

        ChoiceBox<String> processorCB = new ChoiceBox<>();
        processorCB.getItems().add("none");
        mDataProcessorFactories.forEach(processor -> {
            processorCB.getItems().add(processor.getType());
        });

        processorCB.getSelectionModel().selectFirst();

        dialogPaneContent.getChildren().add(processorCB);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        Optional<ButtonType> result = alert.showAndWait();
        result.filter(buttonType -> buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE)
              .ifPresent(buttonType -> {
                  saveFile(formatCB.getSelectionModel().getSelectedItem(), processorCB.getSelectionModel()
                                                                                      .getSelectedItem());
              });
    }

    private void saveFile(String format, String processor) {
        FileOutputStream fileOS = null;
        try {
            System.out.println("saveFile: " + format + "\t" + processor);

            DataTransformer dataTransformer = createTransformer(format);
            if (!processor.equals("none")) {
                dataTransformer = createTransformer(processor, dataTransformer);
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file");
            File file = fileChooser.showSaveDialog(mRootContainer.getScene().getWindow());

            if (file == null) {
                return;
            }

            ByteArrayOutputStream transformedData = dataTransformer.transform(mGameObjects.toArray());

            file = new File(file.getPath() + "." + dataTransformer.getFormat());
            fileOS = new FileOutputStream(file);
            transformedData.writeTo(fileOS);

            showSuccessAlert();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert();
        } finally {
            if (fileOS != null) {
                try {
                    fileOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saving...");

        alert.setHeaderText(null);
        alert.setContentText("The file was successfully saved");

        alert.showAndWait();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Saving...");

        alert.setHeaderText(null);
        alert.setContentText("Something went wrong...");

        alert.showAndWait();
    }

    private DataTransformer createTransformer(String type) {
        Optional<FormatDataTransformerFactory> factory = mFormatDataTransformerFactories.stream()
                                                                                        .filter(f -> f.getType()
                                                                                                      .equals(type))
                                                                                        .findFirst();
        if (!factory.isPresent()) {
            throw new RuntimeException("Factory " + type + " not found!");
        }

        return factory.get().create();
    }

    private DataTransformer createTransformer(String type, DataTransformer dataTransformer) {
        Optional<DataProcessorFactory> factory = mDataProcessorFactories.stream().filter(f -> f.getType().equals(type))
                                                                        .findFirst();
        if (!factory.isPresent()) {
            throw new RuntimeException("Factory " + type + " not found!");
        }

        return factory.get().create(dataTransformer);
    }
}
