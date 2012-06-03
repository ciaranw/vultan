package com.ciaranwood.vultan;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SwfReader {
    
    private SwfReader() {}
    
    public static ByteBuffer readFromClasspath(String location) throws IOException {
        InputStream is = SwfReader.class.getClassLoader().getResourceAsStream(location);

        byte[] data = IOUtils.toByteArray(is);

        return ByteBuffer.wrap(data);
    }

    public static ByteBuffer readFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        byte[] data = IOUtils.toByteArray(is);

        return ByteBuffer.wrap(data);
    }

}
