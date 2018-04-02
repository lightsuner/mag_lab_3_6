package com.zipdataprocessor;

import com.datatransformerapi.DataProcessor;
import com.datatransformerapi.DataTransformer;
import com.datatransformerapi.DataTransformerException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipDataProcessor extends DataProcessor {
    private final static int BUFFER = 2048;
    private final static String ENTRY_NAME = "data";

    public ZipDataProcessor(DataTransformer dataTransformer) {
        super(dataTransformer);
    }

    @Override
    public ByteArrayOutputStream transform(Object object) throws DataTransformerException {
        ByteArrayOutputStream prevOutputStream = getDataTransformer().transform(object);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(prevOutputStream.toByteArray());

        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();

        try {
            compress(inputStream, newOutputStream);
        } catch (IOException e) {
            throw new DataTransformerException(e);
        }

        return newOutputStream;
    }

    @Override
    public Object transform(InputStream inputStream) throws DataTransformerException {

        ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();

        try {
            decompress(inputStream, tmpOutputStream);
        } catch (IOException e) {
            throw new DataTransformerException(e);
        }

        return getDataTransformer().transform(new ByteArrayInputStream(tmpOutputStream.toByteArray()));

    }

    @Override
    protected String getFormatModifier() {
        return "zip";
    }

    private void compress(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[BUFFER];
        ZipOutputStream zos = new ZipOutputStream(out);
        zos.putNextEntry(new ZipEntry(getEntryName()));

        int length;
        while ((length = in.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }
        zos.closeEntry();
        zos.close();
    }

    private void decompress(InputStream in, OutputStream out) throws IOException {
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry entry;

        while ((entry = zin.getNextEntry()) != null) {
            if (entry.getName().equals(getEntryName())) {
                final int BUFFER = 2048;
                byte buffer[] = new byte[BUFFER];

                int size;
                while ((size = zin.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, size);
                }

                break;
            }
        }
    }

    private String getEntryName() {
        return ENTRY_NAME + "." + getDataTransformer().getFormat();
    }
}
