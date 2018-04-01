package com.bsuir.lab36.helpers;

import com.bsuir.lab36.utils.Callback;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UIHelper {
    public UIHelper() {
    }

    public TextField createTextField(FieldHelper fieldHelper) {
        TextField fieldEdit = new TextField();

        if (fieldHelper.isIntField()) {
            fieldEdit.setText(Integer.toString((Integer) fieldHelper.getFieldValue()));
            if (fieldHelper.hasSetter()) {
                fieldEdit.setOnKeyReleased(event -> {
                    try {
                        fieldHelper.setFieldValue(Integer.parseInt(fieldEdit.getText()));
                    } catch (NumberFormatException e) {
                        fieldHelper.setFieldValue(0);
                    }
                });
            } else {
                fieldEdit.setDisable(true);
            }
        } else {
            fieldEdit.setText((String) fieldHelper.getFieldValue());
            if (fieldHelper.hasSetter()) {
                fieldEdit.setOnKeyReleased(event -> fieldHelper.setFieldValue(fieldEdit.getText()));
            } else {
                fieldEdit.setDisable(true);
            }
        }

        return fieldEdit;
    }

    public CheckBox createCheckBox(FieldHelper fieldHelper) {
        CheckBox fieldCheckBox = new CheckBox();
        fieldCheckBox.setSelected((boolean) fieldHelper.getFieldValue());
        if (fieldHelper.hasSetter()) {
            fieldCheckBox.setOnMouseReleased(event -> fieldHelper.setFieldValue(fieldCheckBox.isSelected()));
        } else {
            fieldCheckBox.setDisable(true);
        }

        return fieldCheckBox;
    }

    public ComboBox createComboBox(FieldHelper fieldHelper) {
        ComboBox<Enum> fieldComboBox = new ComboBox<>();
        Field[] enumFields = fieldHelper.getFieldType().getFields();
        for (Field enumField : enumFields) {
            fieldComboBox.getItems().add(Enum.valueOf(fieldHelper.getFieldType(), enumField.getName()));
        }
        fieldComboBox.getSelectionModel().select((Enum) fieldHelper.getFieldValue());

        if (fieldHelper.hasSetter()) {
            fieldComboBox
                .setOnAction(event -> fieldHelper.setFieldValue(fieldComboBox.getSelectionModel().getSelectedItem()));
        } else {
            fieldComboBox.setDisable(true);
        }

        return fieldComboBox;
    }

    public VBox createVBox(FieldHelper fieldHelper) {
        if (fieldHelper.getFieldValue() == null) {
            if (!fieldHelper.hasSetter()) {
                return null;
            }
            Object objectField = null;
            try {
                objectField = fieldHelper.getFieldType().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            fieldHelper.setFieldValue(objectField);
        }
        return createVBoxContainer(fieldHelper.getFieldValue());
    }

    public VBox createVBoxContainer(Object object) {
        VBox fieldsBox = new VBox();
        fieldsBox.setMinWidth(300);
        fieldsBox.setMaxWidth(300);
        fieldsBox.setPadding(new Insets(15));
        fieldsBox.setSpacing(10);

        fieldsBox.setStyle("-fx-background-color: aquamarine");

        if (object == null) {
            return fieldsBox;
        }

        List<Field> fields = getFields(object);

        for (Field field : fields) {
            FieldHelper fieldHelper = new FieldHelper(object, field);

            if (!fieldHelper.hasGetter()) {
                continue;
            }

            Label fieldLabel = new Label(fieldHelper.getFieldTitle());
            Node fieldNode = null;

            if (fieldHelper.isIntField()) {
                fieldNode = createTextField(fieldHelper);
            } else if (fieldHelper.isStringField()) {
                fieldNode = createTextField(fieldHelper);
            } else if (fieldHelper.isBooleanField()) {
                fieldNode = createCheckBox(fieldHelper);
            } else if (fieldHelper.isEnumField()) {
                fieldNode = createComboBox(fieldHelper);
            } else {
                fieldNode = createVBox(fieldHelper);
            }

            if (fieldNode == null) {
                continue;
            }
            fieldsBox.getChildren().addAll(fieldLabel, fieldNode);
        }

        return fieldsBox;
    }

    private <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }


}
