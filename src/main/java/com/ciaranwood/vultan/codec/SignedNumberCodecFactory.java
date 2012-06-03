package com.ciaranwood.vultan.codec;

import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.Resolver;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.el.Expression;
import org.codehaus.preon.el.Expressions;

import java.lang.reflect.AnnotatedElement;

public class SignedNumberCodecFactory implements CodecFactory {

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(metadata != null && metadata.isAnnotationPresent(SignedNumber.class)) {
            SignedNumber annotation = metadata.getAnnotation(SignedNumber.class);
            Expression<Integer, Resolver> sizeExpr = Expressions.createInteger(context, annotation.size());
            return (Codec<T>) new SignedNumberCodec(sizeExpr);
        } else {
            return null;
        }
    }

}
