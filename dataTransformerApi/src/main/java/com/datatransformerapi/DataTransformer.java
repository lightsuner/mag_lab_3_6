package com.datatransformerapi;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public interface DataTransformer {
    ByteArrayOutputStream transform(Object object) throws DataTransformerException;

    Object transform(InputStream inputStream) throws DataTransformerException;

    String getFormat();
}
