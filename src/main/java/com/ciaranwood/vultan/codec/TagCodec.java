package com.ciaranwood.vultan.codec;

import com.ciaranwood.vultan.tags.TagType;
import com.ciaranwood.vultan.types.RecordHeader;
import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.buffer.DefaultBitBuffer;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.codec.BindingsContext;
import org.codehaus.preon.el.BindingException;
import org.codehaus.preon.el.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TagCodec implements Codec<List> {

    private final CodecFactory factory;

    private static final Logger log = LoggerFactory.getLogger(TagCodec.class);

    public TagCodec(CodecFactory factory) {
        this.factory = factory;
    }

    public List decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        boolean end = false;
        List<Object> tags = new ArrayList<Object>();

        while(!end) {
            RecordHeader header = decodeRecordHeader(buffer);
            log.debug("processing {}", header);
            TagType tagType = header.getTagType();

            if(TagType.End == tagType) {
                end = true;
            } else if(TagType.Unknown == tagType) {
                //skip tag
            } else if(header.getLength().equals(0)) {
                Object tag = null;
                try {
                    tag = builder.create(tagType.getType());
                } catch (InstantiationException e) {
                    log.error("unable to create class " + header.getTagType(), e);
                } catch (IllegalAccessException e) {
                    log.error("unable to create class " + header.getTagType(), e);
                }
                tags.add(tag);

            } else {
                Codec tagCodec = factory.create(null, tagType.getType(), null);
                Object tag = tagCodec.decode(buffer, resolver, builder);
                tags.add(tag);
            }

        }
        return tags;
    }

    private RecordHeader decodeRecordHeader(BitBuffer buffer) {
        byte first = buffer.readAsByte(8, ByteOrder.LittleEndian);
        byte last = buffer.readAsByte(8, ByteOrder.LittleEndian);

        byte[] rewritten = new byte[] {last, first};
        BitBuffer newBuffer = new DefaultBitBuffer(ByteBuffer.wrap(rewritten));

        Integer tagCode = newBuffer.readAsInt(10);
        Integer length = newBuffer.readAsInt(6);

        if(length.equals(0x3F)) {
            length = buffer.readAsInt(32, ByteOrder.LittleEndian);
        }

        TagType type;
        try {
            type = TagType.getByCode(tagCode);
        } catch(IllegalArgumentException e) {
            Integer skipBits = length * 8;
            log.debug("unknown tag type {}, skipping {} bytes", tagCode, skipBits / 8);
            buffer.setBitPos(buffer.getActualBitPos() + skipBits);
            type = TagType.Unknown;
        }

        return new RecordHeader(type, length);
    }

    public void encode(List value, BitChannel channel, Resolver resolver) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Expression<Integer, Resolver> getSize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CodecDescriptor getCodecDescriptor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class<?>[] getTypes() {
        return new Class<?>[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class<?> getType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return "Tag Codec";
    }
}
