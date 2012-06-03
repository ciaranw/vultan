package com.ciaranwood.vultan.codec;

import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.Resolver;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.el.Expression;
import org.codehaus.preon.el.Expressions;

import java.lang.reflect.AnnotatedElement;
import java.math.BigDecimal;

public class FixedNumberCodecFactory implements CodecFactory {

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(!type.equals(BigDecimal.class)) {
            return null;   
        }
        
        if(metadata != null && metadata.isAnnotationPresent(Fixed.class)) {
            Fixed fixedMetaData = metadata.getAnnotation(Fixed.class);
            String size = fixedMetaData.size();
            Expression<Integer, Resolver> sizeExpr = Expressions.createInteger(context, size);
            
            Expression<Integer, Resolver> lowBitSizeExpr = null;
            String lowBitSize = fixedMetaData.lowBitSize();
            if(!lowBitSize.isEmpty()) {
                lowBitSizeExpr = Expressions.createInteger(context, lowBitSize);
            }

            return (Codec<T>) new FixedNumberCodec(sizeExpr, lowBitSizeExpr);
        } else {
            return null;
        }
    }
}
