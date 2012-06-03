package com.ciaranwood.vultan;

import com.ciaranwood.vultan.tags.SWFHeader;
import org.codehaus.preon.Codec;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.DefaultBuilder;
import org.codehaus.preon.buffer.BitBuffer;

public class SwfHeaderParser {

    private final CodecFactory factory;
    private final BitBuffer buffer;

    public SwfHeaderParser(CodecFactory factory, BitBuffer buffer) {
        this.factory = factory;
        this.buffer = buffer;
    }

    public SWFHeader parse() {
        Codec<SWFHeader> headerCodec = factory.create(null, SWFHeader.class, null);

        try {
            return headerCodec.decode(buffer, null, new DefaultBuilder());
        } catch (DecodingException e) {
            throw new RuntimeException(e);
        }
    }
}
