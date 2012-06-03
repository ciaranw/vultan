package com.ciaranwood.vultan;

import org.codehaus.preon.DecodingException;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

public class SWFParserTest {

    private SwfParser testObj;

    @Test
    public void test() throws IOException, DecodingException {
        ByteBuffer data = SwfReader.readFromClasspath("test.swf");
        testObj = new SwfParser(data);

        SWF swf = testObj.parse();

        assertEquals(4, swf.getTags().size());
    }

    @Test
    public void testCompressed() throws IOException, DecodingException {
        ByteBuffer data = SwfReader.readFromClasspath("test-compressed.swf");
        testObj = new SwfParser(data);

        SWF swf = testObj.parse();

        assertEquals(4, swf.getTags().size());
    }
}
