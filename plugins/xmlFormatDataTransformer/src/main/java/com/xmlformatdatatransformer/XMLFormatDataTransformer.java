package com.xmlformatdatatransformer;

import com.datatransformerapi.DataTransformerException;
import com.datatransformerapi.FormatDataTransformer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class XMLFormatDataTransformer extends FormatDataTransformer {
    @Override
    public ByteArrayOutputStream transform(Object object) throws DataTransformerException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
        xmlEncoder.writeObject(object);
        xmlEncoder.close();

        return outputStream;
    }

    @Override
    public Object transform(InputStream inputStream) throws DataTransformerException {
        XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
        Object data = xmlDecoder.readObject();
        xmlDecoder.close();
        return data;
    }

    @Override
    public String getFormat() {
        return "xml";
    }
}
