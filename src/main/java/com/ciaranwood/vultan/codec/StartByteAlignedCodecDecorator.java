package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AnnotatedElement;

public class StartByteAlignedCodecDecorator implements CodecDecorator {

    public <T> Codec<T> decorate(Codec<T> codec, AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if (metadata != null && metadata.isAnnotationPresent(StartByteAligned.class)) {
            return new StartByteAlignedCodec<T>(codec);
        } else {
            return codec;
        }
    }

    private static class StartByteAlignedCodec<T> extends AbstractDecoratedCodec<T> {
        private static final Logger log = LoggerFactory.getLogger(StartByteAlignedCodec.class);

        private StartByteAlignedCodec(Codec<T> codec) {
            super(codec);
        }

        @Override
        public T decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
            long pos = buffer.getBitPos() % 8;
            if (pos > 0) {
                log.debug("byte aligning before decoding");
                buffer.setBitPos(buffer.getBitPos() + 8 - pos);
            }
            return codec.decode(buffer, resolver, builder);
        }
    }
}
