package com.ciaranwood.vultan;

import org.apache.commons.io.IOUtils;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Main {
    
    public static void main(String[] args) throws IOException, DecodingException {
        File file = new File("../swf2html5/src/main/resources/avatar_binary_dump.swf");
        ByteBuffer buffer = SwfReader.readFromFile(file);

        SwfParser parser = new SwfParser(buffer);

        SWF swf = parser.parse();
    }
}
