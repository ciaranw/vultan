package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;

import java.io.IOException;
import java.math.BigDecimal;

public class FixedNumberCodec implements Codec<BigDecimal> {
    
    private final Expression<Integer, Resolver> lowSize;
    private final Expression<Integer, Resolver> totalSize;

    public FixedNumberCodec(Expression<Integer, Resolver> totalSize, Expression<Integer, Resolver> lowSize) {
        this.totalSize = totalSize;
        this.lowSize = lowSize;
    }

    public BigDecimal decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        Integer totalSizeValue = totalSize.eval(resolver);
        Integer lowSizeValue = getLowSizeValue(totalSizeValue, resolver);

        Integer low;
        Integer high;

        if(lowSizeValue >= totalSizeValue) {
            low = buffer.readAsInt(totalSizeValue, ByteOrder.LittleEndian);
            high = 0;
        } else {
            Integer highSize = totalSizeValue - lowSizeValue;
            low = buffer.readAsInt(lowSizeValue, ByteOrder.LittleEndian);
            high = buffer.readAsInt(highSize, ByteOrder.LittleEndian);
        }

        BigDecimal lowBD = new BigDecimal(low).divide(new BigDecimal(getMax(lowSizeValue)));
        BigDecimal highBD = new BigDecimal(high);

        return highBD.add(lowBD);
    }

    private Integer getLowSizeValue(Integer totalSizeValue, Resolver resolver) {
        if(lowSize == null) {
            return totalSizeValue / 2;
        } else {
            return lowSize.eval(resolver);
        }
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
        return "Fixed number codec";
    }
}
