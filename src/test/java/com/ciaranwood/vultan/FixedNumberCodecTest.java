package com.ciaranwood.vultan;

import com.ciaranwood.vultan.codec.FixedNumberCodec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.preon.Codec;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.Resolver;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.codehaus.preon.el.Expression;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FixedNumberCodecTest {

    @Test
    public void testParse8_8Fixed() throws DecodingException, DecoderException {
        byte[] data = Hex.decodeHex("000C".toCharArray());

        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        Codec<BigDecimal> testObj = new FixedNumberCodec(createStubExpression(16), createStubExpression(8));
        
        BigDecimal result = testObj.decode(buffer, null, null);
        
        assertEquals(new BigDecimal("12"), result);
    }

    @Test
    public void testParse16_16Fixed() throws DecodingException, DecoderException {
        byte[] data = Hex.decodeHex("00800700".toCharArray());

        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        Codec<BigDecimal> testObj = new FixedNumberCodec(createStubExpression(32), null);

        BigDecimal result = testObj.decode(buffer, null, null);

        assertEquals(new BigDecimal("7.5"), result);
    }
    
    private Expression<Integer, Resolver> createStubExpression(Integer value) {
        Expression<Integer, Resolver> stub = mock(Expression.class);
        when(stub.eval(any(Resolver.class))).thenReturn(value);
        return stub;
    }
}
