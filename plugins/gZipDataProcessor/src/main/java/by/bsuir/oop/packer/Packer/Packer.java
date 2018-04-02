/**
 * The origin: https://github.com/atatarenko/communication-device-serialization
 */
package by.bsuir.oop.packer.Packer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Packer {
    protected static int BUFFER = 2048;
    protected String extension = "none"; // default value

    public abstract void compress(InputStream in, OutputStream out) throws IOException;

    public abstract void decompress(InputStream in, OutputStream out) throws IOException;

    public String getExtension() {
        if (extension.equals("none"))
            return "";
        else
            return "." + extension;
    }

    public String toString() {
        return Character.toUpperCase(extension.charAt(0)) + extension.substring(1);
    }
}
