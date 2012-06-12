package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AnnotatedElement;

public class VersionedCodecDecorator implements CodecDecorator {

    public <T> Codec<T> decorate(Codec<T> codec, AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(type.isAnnotationPresent(Versioned.class)) {
            Versioned annotation = type.getAnnotation(Versioned.class);
            return new VersionedCodec<T>(codec, annotation.value(), type);
        } else {
            return codec;
        }
    }

    public static class VersionedCodec<T> extends AbstractDecoratedCodec<T> {

        private final Integer version;
        private final Class<T> type;

        private static final Logger log = LoggerFactory.getLogger(VersionedCodec.class);

        public VersionedCodec(Codec<T> codec, Integer version, Class<T> type) {
            super(codec);
            this.version = version;
            this.type = type;
            log.debug("creating versioned codec of version {} for {}", version, type);
        }

        @Override
        public T decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
            VersionStack.INSTANCE.pushVersion(version);
            try {
                return super.decode(buffer, resolver, builder);
            } finally {
                VersionStack.INSTANCE.popVersion();
            }
        }
    }

}
