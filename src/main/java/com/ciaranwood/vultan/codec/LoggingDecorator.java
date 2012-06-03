package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public class LoggingDecorator implements CodecDecorator {

    private static final Logger log = LoggerFactory.getLogger(LoggingDecorator.class);

    public <T> Codec<T> decorate(final Codec<T> codec, AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(type.isAnnotationPresent(Log.class) ||
                (metadata != null && metadata.isAnnotationPresent(Log.class))) {
            Field field = null;
            if(metadata instanceof Field) {
                field = (Field) metadata;
            }
            return new LoggingCodec<T>(codec, field);
        } else {
            return codec;
        }
    }

    private static class LoggingCodec<T> extends AbstractDecoratedCodec<T> {
        private final Field field;

        private LoggingCodec(Codec<T> codec, Field field) {
            super(codec);
            this.field = field;
        }

        @Override
        public T decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
            T result = codec.decode(buffer, resolver, builder);
            String fieldName = (field == null) ? "" : field.getName();
            log.debug("{} produced {} for {}", new Object[] {codec, result, fieldName});
            return result;
        }
    }
}
