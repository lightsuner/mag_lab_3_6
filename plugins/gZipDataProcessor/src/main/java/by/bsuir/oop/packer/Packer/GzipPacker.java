/**
 * The origin: https://github.com/atatarenko/communication-device-serialization
 */
package by.bsuir.oop.packer.Packer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipPacker extends Packer {

    public GzipPacker() {
        this.extension = "gzip";
    }

    @Override
    public void compress(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[BUFFER];
        GZIPOutputStream zos = new GZIPOutputStream(out);

        int length;
        while ((length = in.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }
        zos.close();
    }

    @Override
    public void decompress(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[BUFFER];
        GZIPInputStream zin = new GZIPInputStream(in);

        int size;
        while ((size = zin.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, size);
        }
    }
}
