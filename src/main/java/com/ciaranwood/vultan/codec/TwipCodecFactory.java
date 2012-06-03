package com.ciaranwood.vultan.codec;

import com.ciaranwood.vultan.types.Twip;
import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.codec.NumericCodec;

import java.lang.reflect.AnnotatedElement;

public class TwipCodecFactory implements CodecFactory {

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        if(type.equals(Twip.class)) {
            CodecFactory factory = CustomTypes.getCodecFactory(context);
            Codec<Integer> codec = factory.create(metadata, Integer.class, context);
            return (Codec<T>) new TwipCodec(codec);
        } else {
            return null;
        }
    }
}
