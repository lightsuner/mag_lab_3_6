package com.binformatdatatransformer;

import com.datatransformerapi.DataTransformerException;
import com.datatransformerapi.FormatDataTransformer;

import java.io.*;

public class BinFormatDataTransformer extends FormatDataTransformer {
    @Override
    public ByteArrayOutputStream transform(Object object) throws DataTransformerException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            throw new DataTransformerException(e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return outputStream;
    }

    @Override
    public Object transform(InputStream inputStream) throws DataTransformerException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new PluginObjectInputStream(inputStream);

            Object object = objectInputStream.readObject();

            return object;
        } catch (Exception e) {
            throw new DataTransformerException(e);
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getFormat() {
        return "bin";
    }
}
