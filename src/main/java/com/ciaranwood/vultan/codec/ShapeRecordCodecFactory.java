package com.ciaranwood.vultan.codec;

import com.ciaranwood.vultan.types.ShapeRecord;
import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.el.Expressions;

import java.lang.reflect.AnnotatedElement;

public class ShapeRecordCodecFactory implements CodecFactory {

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(type.equals(ShapeRecord.class)) {
            return (Codec<T>) new ShapeRecordCodec(context);
        } else {
            return null;
        }
    }
}
