package com.bsuir.lab36.helpers;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class FieldHelper {
    private final String mMethodNameBody;
    private String mFieldName;
    private Class mFieldType;
    private Object mObject;
    private Field mField;

    private Method mGetMethod, mSetMethod;

    public FieldHelper(Object object, Field field) {
        mFieldName = field.getName();
        mFieldType = field.getType();
        mObject = object;
        mField = field;

        mMethodNameBody = getFieldMethodName();

        try {
            this.mGetMethod = object.getClass().getMethod("get" + mMethodNameBody);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (isFinal()) {
            return;
        }

        try {
            this.mSetMethod = object.getClass().getMethod("set" + mMethodNameBody, mFieldType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinal() {
        return Modifier.isFinal(mField.getModifiers());
    }

    public String getFieldTitle() {
        return String.join(" ", StringUtils.splitByCharacterTypeCamelCase(mMethodNameBody));
    }

    public String getFieldName() {
        return mFieldName;
    }

    public Class getFieldType() {
        return mFieldType;
    }

    public Object getFieldValue() {
        Object o = null;

        try {
            o = mGetMethod.invoke(mObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return o;
    }

    public void setFieldValue(Object arg) {
        try {
            mSetMethod.invoke(mObject, arg);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean isIntField() {
        return mFieldType.equals(int.class);
    }

    public boolean isStringField() {
        return mFieldType.equals(String.class);
    }

    public boolean isBooleanField() {
        return mFieldType.equals(boolean.class);
    }

    public boolean isEnumField() {
        return mFieldType.isEnum();
    }

    public boolean isPrimitive() {
        return isBooleanField() | isIntField() | isEnumField() | isStringField();
    }

    public boolean hasGetter() {
        return mGetMethod != null;
    }

    public boolean hasSetter() {
        return mSetMethod != null;
    }

    private String getFieldMethodName() {
        String fieldMethodName = mFieldName;
        if (fieldMethodName.charAt(0) == 'm') {
            fieldMethodName = mFieldName.substring(1);
        }

        fieldMethodName = camelCase(fieldMethodName);

        return fieldMethodName;
    }

    private String camelCase(String in) {
        if (in == null || in.length() < 1) {
            return "";
        } //validate in
        String out = "";
        for (String part : in.toLowerCase().split("_")) {
            if (part.length() < 1) { //validate length
                continue;
            }
            out += part.substring(0, 1).toUpperCase();
            if (part.length() > 1) { //validate length
                out += part.substring(1);
            }
        }
        return out;
    }
}