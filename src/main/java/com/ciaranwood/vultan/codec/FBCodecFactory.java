package com.ciaranwood.vultan.codec;

import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.el.Expression;
import org.codehaus.preon.el.Expressions;

import java.lang.reflect.AnnotatedElement;

public class FBCodecFactory implements CodecFactory {

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(metadata != null && metadata.isAnnotationPresent(FB.class)) {
            FB annotation = metadata.getAnnotation(FB.class);
            Expression size = Expressions.createInteger(context, annotation.size());
            return (Codec<T>) new FBCodec(size);
        } else {
            return null;
        }

    }
}
