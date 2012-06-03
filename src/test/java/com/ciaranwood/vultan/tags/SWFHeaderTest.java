package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.SwfHeaderParser;
import com.ciaranwood.vultan.SwfReader;
import com.ciaranwood.vultan.codec.CustomTypes;
import com.ciaranwood.vultan.types.Rect;
import com.ciaranwood.vultan.types.Twip;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

public class SWFHeaderTest {
    
    private SWFHeader testObj;
    
    @Before
    public void setup() throws IOException, DecodingException {
        ByteBuffer data = SwfReader.readFromClasspath("test.swf");
        CodecFactory factory = CustomTypes.getCodecFactory(null);
        testObj = new SwfHeaderParser(factory, new DefaultBitBuffer(data)).parse();
    }
    
    @Test
    public void testParseSignature() {
        assertEquals("FWS", testObj.signature);
    }
    
    @Test
    public void testParseVersion() {
        assertEquals(Integer.valueOf(3), testObj.version);
    }
    
    @Test
    public void testParseFileSize() {
        assertEquals(Integer.valueOf(79), testObj.fileLength);
    }
    
    @Test
    public void testParseFrameSize() {
        Rect frameSize = testObj.frameSize;

        assertEquals(Integer.valueOf(15), frameSize.numBits);
        assertEquals(new Twip(0), frameSize.xMin);
        assertEquals(new Twip(11000), frameSize.xMax);
        assertEquals(new Twip(0), frameSize.yMin);
        assertEquals(new Twip(8000), frameSize.yMax);
    }

    @Test
    public void testParseFrameRate() {
        assertEquals(new BigDecimal("12"), testObj.frameRate);
    }
    
    @Test
    public void testParseFrameCount() {
        assertEquals(Integer.valueOf(1), testObj.frameCount);
    }
}
