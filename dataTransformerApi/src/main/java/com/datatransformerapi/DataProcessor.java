package com.datatransformerapi;

public abstract class DataProcessor implements DataTransformer {
    private DataTransformer mDataTransformer;

    public DataProcessor(DataTransformer dataTransformer) {
        mDataTransformer = dataTransformer;
    }

    protected DataTransformer getDataTransformer() {
        return mDataTransformer;
    }
}
