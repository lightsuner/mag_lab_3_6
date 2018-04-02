package com.binformatdatatransformer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class PluginObjectInputStream extends ObjectInputStream {
    public PluginObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(desc.getName());
        } catch (ClassNotFoundException e) {
            // skip
        }

        return super.resolveClass(desc);
    }
}
