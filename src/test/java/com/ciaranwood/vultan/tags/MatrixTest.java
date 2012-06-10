package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.CustomTypes;
import com.ciaranwood.vultan.types.Matrix;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.DefaultBuilder;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixTest {

    public static final double DELTA = 0.000001;

    @Test
    public void testParseMatrix() throws DecodingException {
        byte[] data = new byte[]{(byte) 0xC9, 0x00, 0x00, 0x3F, (byte) 0xCF, 0x68, 0x00, (byte) 0xB9, 0x5F, (byte) 0xFE, (byte) 0xA0, (byte) 0x70};
        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        CodecFactory factory = CustomTypes.getCodecFactory(null);

        Codec<Matrix> codec = factory.create(null, Matrix.class, null);

        Matrix result = codec.decode(buffer, null, new DefaultBuilder());

        assertTrue(result.hasScale);
        assertEquals(1.0, result.scaleX.doubleValue(), DELTA);
        assertEquals(0.99702453613, result.scaleY.doubleValue(), DELTA);

        assertTrue(result.hasRotate);
        assertEquals(0.0, result.rotateSkew0.doubleValue(), DELTA);
        assertEquals(-0.0010833740, result.rotateSkew1.doubleValue(), DELTA);

        assertEquals(new BigDecimal("-0.1"), result.translateX.getPixels());
        assertEquals(new BigDecimal("-38.25"), result.translateY.getPixels());
    }

}
