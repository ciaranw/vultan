package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;
import org.codehaus.preon.el.ast.IntegerNode;

import java.io.IOException;
import java.math.BigDecimal;

public class FBCodec implements Codec<BigDecimal> {

    private final Expression<Integer, Resolver> totalSize;

    public FBCodec(Expression<Integer, Resolver> totalSize) {
        this.totalSize = totalSize;
    }

    public BigDecimal decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        Integer totalSizeValue = totalSize.eval(resolver);
        Integer lowSizeValue = 16;

        Integer signed = (Integer) new SignedNumberCodec(totalSize, ByteOrder.BigEndian).decode(buffer, resolver, builder);
        float divisor = (float) Math.pow(2, 16);

        float value = signed / divisor;

        return new BigDecimal(value);

    }

    private int getMax(int nrBits) {
        return (0xFFFFFFFF >>> (32 - nrBits)) + 1;
    }

    public void encode(BigDecimal value, BitChannel channel, Resolver resolver) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Expression<Integer, Resolver> getSize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CodecDescriptor getCodecDescriptor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class<?>[] getTypes() {
        return new Class<?>[] {BigDecimal.class};
    }

    public Class<?> getType() {
        return BigDecimal.class;
    }

    @Override
    public String toString() {
        return "FB Fixed number codec";
    }
}
