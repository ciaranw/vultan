package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;

import java.io.IOException;

public abstract class AbstractDecoratedCodec<T> implements Codec<T> {

    protected final Codec<T> codec;

    protected AbstractDecoratedCodec(Codec<T> codec) {
        this.codec = codec;
    }

    public T decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        return codec.decode(buffer, resolver, builder);
    }

    public void encode(T value, BitChannel channel, Resolver resolver) throws IOException {
        codec.encode(value, channel, resolver);
    }

    public Expression<Integer, Resolver> getSize() {
        return codec.getSize();
    }

    public CodecDescriptor getCodecDescriptor() {
        return codec.getCodecDescriptor();
    }

    public Class<?>[] getTypes() {
        return codec.getTypes();
    }

    public Class<?> getType() {
        return codec.getType();
    }
}
