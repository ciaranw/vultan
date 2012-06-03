package com.ciaranwood.vultan.codec;

import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.ResolverContext;

import java.lang.reflect.AnnotatedElement;

public class TagCodecFactory implements CodecFactory {

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type, ResolverContext context) {
        return (Codec<T>) new TagCodec(CustomTypes.getCodecFactory(null));
    }
}
