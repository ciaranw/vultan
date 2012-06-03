package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;

import java.lang.reflect.AnnotatedElement;

public class ReadZeroLengthCodecDecorator implements CodecDecorator {

    public <T> Codec<T> decorate(final Codec<T> codec, AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(metadata != null && metadata.isAnnotationPresent(ReadZeroLength.class)) {
            return new ReadZeroLengthCodec<T>(codec);
        } else {
            return codec;
        }
    }

    private static class ReadZeroLengthCodec<T> extends AbstractDecoratedCodec<T> {
        private ReadZeroLengthCodec(Codec<T> codec) {
            super(codec);
        }

        @Override
        public T decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
            ReadZeroLengthBitBuffer readZeroLengthBuffer = new ReadZeroLengthBitBuffer(buffer);
            return codec.decode(readZeroLengthBuffer, resolver, builder);
        }
    }

}
