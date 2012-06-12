package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.CustomTypes;
import com.ciaranwood.vultan.codec.VersionStack;
import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.DefaultBuilder;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.junit.After;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class LineStyle3Test {

    @Test
    public void testParseRGB() throws DecodingException {
        VersionStack.INSTANCE.pushVersion(1);
        byte[] data = new byte[] {0x01, 0x01, 0x00, (byte) 0xaa, (byte) 0xff, (byte) 0xff};
        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        CodecFactory factory = CustomTypes.getCodecFactory(null);
        Codec<LineStyle> codec = factory.create(null, LineStyle.class, null);

        LineStyle result = codec.decode(buffer, null, new DefaultBuilder());

        assertNotNull(result.colorv1);
        assertEquals(Integer.valueOf(0), result.colorv1.red);
        assertEquals(Integer.valueOf(170), result.colorv1.green);
        assertEquals(Integer.valueOf(255), result.colorv1.blue);

    }

    @Test
    public void testParseRGBA() throws DecodingException {
        VersionStack.INSTANCE.pushVersion(3);
        byte[] data = new byte[] {0x01, 0x01, 0x00, (byte) 0xaa, (byte) 0xff, (byte) 0xff};
        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        CodecFactory factory = CustomTypes.getCodecFactory(null);
        Codec<LineStyle> codec = factory.create(null, LineStyle.class, null);

        LineStyle result = codec.decode(buffer, null, new DefaultBuilder());

        assertNotNull(result.colorv3);

        assertEquals(Integer.valueOf(0), result.colorv3.red);
        assertEquals(Integer.valueOf(170), result.colorv3.green);
        assertEquals(Integer.valueOf(255), result.colorv3.blue);
        assertEquals(Integer.valueOf(255), result.colorv3.alpha);

    }

    @After
    public void cleanup() {
        VersionStack.INSTANCE.popVersion();
    }
}
