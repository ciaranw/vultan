package com.ciaranwood.vultan.codec;

import com.ciaranwood.vultan.types.Twip;
import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.codec.NumericCodec;
import org.codehaus.preon.el.Expression;

import java.io.IOException;

public class TwipCodec implements Codec<Twip> {
    
    private final Codec<Integer> codec;

    public TwipCodec(Codec<Integer> codec) {
        this.codec = codec;
    }

    public Twip decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        Integer number = codec.decode(buffer, resolver, builder);
        return new Twip(number);
    }

    public void encode(Twip value, BitChannel channel, Resolver resolver) throws IOException {
        codec.encode(value.getTwips(), channel, resolver);
    }

    public Expression<Integer, Resolver> getSize() {
        return codec.getSize();
    }

    public CodecDescriptor getCodecDescriptor() {
        return codec.getCodecDescriptor();
    }

    public Class<?>[] getTypes() {
        return new Class<?>[] {Twip.class}; 
    }

    public Class<?> getType() {
        return Twip.class;
    }
}
