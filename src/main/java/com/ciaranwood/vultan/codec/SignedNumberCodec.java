package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.codec.NumericCodec;
import org.codehaus.preon.el.Expression;

import java.io.IOException;

public class SignedNumberCodec implements Codec<Number> {
    
    private final Expression<Integer, Resolver> sizeExpression;
    private final ByteOrder byteOrder;

    public SignedNumberCodec(Expression<Integer, Resolver> sizeExpression, ByteOrder byteOrder) {
        this.sizeExpression = sizeExpression;
        this.byteOrder = byteOrder;
    }

    public Number decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        Integer size = sizeExpression.eval(resolver);
        if(size.equals(0)) {
            return 0;
        }

        if(size.equals(1)) {
            return buffer.readAsInt(1, byteOrder);
        }

        boolean highestBit = buffer.readAsBoolean(byteOrder);

        Integer rest = (Integer) NumericCodec.NumericType.Integer.decode(buffer, size - 1, byteOrder);

        if(highestBit) {
            Integer negate = 1 << (size - 1);
            return -negate + rest;
        } else {
            return rest;
        }
    }

    public void encode(Number value, BitChannel channel, Resolver resolver) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Expression<Integer, Resolver> getSize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CodecDescriptor getCodecDescriptor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class<?>[] getTypes() {
        return new Class<?>[] {Integer.class};
    }

    public Class<?> getType() {
        return Integer.class;
    }

    @Override
    public String toString() {
        return "Signed number Codec";
    }
}
