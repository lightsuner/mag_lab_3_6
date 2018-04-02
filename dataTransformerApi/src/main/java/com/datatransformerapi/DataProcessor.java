package com.datatransformerapi;

public abstract class DataProcessor implements DataTransformer {
    private DataTransformer mDataTransformer;

    public DataProcessor(DataTransformer dataTransformer) {
        mDataTransformer = dataTransformer;
    }

    protected DataTransformer getDataTransformer() {
        return mDataTransformer;
    }

    @Override
    final public String getFormat() {
        return mDataTransformer.getFormat() + "." + getFormatModifier();
    }

    abstract protected String getFormatModifier();
}
