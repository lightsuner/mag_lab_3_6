package com.datatransformerapi;

import java.io.Serializable;

public interface GameObject extends Serializable {
    String getName();

    String getRace();

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);
}
