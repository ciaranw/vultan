package com.ciaranwood.vultan;

import com.ciaranwood.vultan.codec.FixedNumberCodec;
import com.ciaranwood.vultan.codec.SignedNumberCodec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.preon.Codec;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.Resolver;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.codehaus.preon.el.Expression;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SignedNumberCodecTest {

    @Test
    public void testNegativeSignedNumber() throws DecoderException, DecodingException {
        byte[] data = Hex.decodeHex("FFFE".toCharArray());

        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        Codec<Number> testObj = new SignedNumberCodec(createStubExpression(16), ByteOrder.BigEndian);

        Number result = testObj.decode(buffer, null, null);

        assertEquals(-2, result);
    }

    @Test
    public void testLargeNegativeSignedNumber() throws DecoderException, DecodingException {
        byte[] data = Hex.decodeHex("8001".toCharArray());

        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        Codec<Number> testObj = new SignedNumberCodec(createStubExpression(16), ByteOrder.BigEndian);

        Number result = testObj.decode(buffer, null, null);

        assertEquals(-32767, result);
    }

    @Test
    public void testPositiveSignedNumber() throws DecoderException, DecodingException {
        byte[] data = Hex.decodeHex("0003".toCharArray());

        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        Codec<Number> testObj = new SignedNumberCodec(createStubExpression(16), ByteOrder.BigEndian);

        Number result = testObj.decode(buffer, null, null);

        assertEquals(3, result);
    }

    private Expression<Integer, Resolver> createStubExpression(Integer value) {
        Expression<Integer, Resolver> stub = mock(Expression.class);
        when(stub.eval(any(Resolver.class))).thenReturn(value);
        return stub;
    }
}
