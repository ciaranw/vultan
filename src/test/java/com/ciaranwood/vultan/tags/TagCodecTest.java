package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.CustomTypes;
import com.ciaranwood.vultan.codec.TagCodec;
import com.ciaranwood.vultan.types.RGB;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.DefaultBuilder;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TagCodecTest {

    private TagCodec testObj;

    @Before
    public void setup() {
        CodecFactory factory = CustomTypes.getCodecFactory(null);
        testObj = new TagCodec(factory);
    }

    @Test
    public void testDecodeSetBackgroundColorTag() throws DecodingException {
        byte[] data = new byte[] {0x43, 0x02, (byte)0xff, (byte)0xff, (byte)0xff, 0x00, 0x00, 0x00, 0x00};
        BitBuffer buffer = new DefaultBitBuffer(ByteBuffer.wrap(data));

        List<Object> tags = testObj.decode(buffer, null, new DefaultBuilder());

        assertEquals(1, tags.size());

        assertThat(tags.get(0), instanceOf(SetBackgroundColor.class));

        SetBackgroundColor tag = (SetBackgroundColor) tags.get(0);
        RGB expectedColor = new RGB();
        expectedColor.red = 255;
        expectedColor.blue = 255;
        expectedColor.green = 255;

        assertEquals(expectedColor, tag.backgroundColor);
    }
}
