package com.gzipdataprocessor;

import by.bsuir.oop.packer.Packer.GzipPacker;
import com.datatransformerapi.DataProcessor;
import com.datatransformerapi.DataTransformer;
import com.datatransformerapi.DataTransformerException;

import java.io.*;

public class GZipDataProcessorAdapter extends DataProcessor {
    private final GzipPacker mGZipPacker;

    public GZipDataProcessorAdapter(DataTransformer dataTransformer, GzipPacker packer) {
        super(dataTransformer);
        mGZipPacker = packer;
    }

    @Override
    public ByteArrayOutputStream transform(Object object) throws DataTransformerException {
        ByteArrayOutputStream prevOutputStream = getDataTransformer().transform(object);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(prevOutputStream.toByteArray());

        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();

        try {
            mGZipPacker.compress(inputStream, newOutputStream);
        } catch (IOException e) {
            throw new DataTransformerException(e);
        }

        return newOutputStream;
    }

    @Override
    public Object transform(InputStream inputStream) throws DataTransformerException {

        ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();

        try {
            mGZipPacker.decompress(inputStream, tmpOutputStream);
        } catch (IOException e) {
            throw new DataTransformerException(e);
        }

        return getDataTransformer().transform(new ByteArrayInputStream(tmpOutputStream.toByteArray()));

    }

    @Override
    protected String getFormatModifier() {
        return "gz";
    }
}
