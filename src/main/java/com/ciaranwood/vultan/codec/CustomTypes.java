package com.ciaranwood.vultan.codec;

import org.codehaus.preon.*;

public class CustomTypes {

    public static CodecFactory getCodecFactory(ResolverContext context) {
        return new DefaultCodecFactoryBuilder()
                .codecFactories(new TwipCodecFactory(),
                        new FixedNumberCodecFactory(),
                        new ShapeRecordCodecFactory(),
                        new SignedNumberCodecFactory())
                .codecDecorators(new LoggingDecorator(),
                        new ReadZeroLengthCodecDecorator(),
                        new StartByteAlignedCodecDecorator()
                )
                .context(context)
                .build();
    }
}

