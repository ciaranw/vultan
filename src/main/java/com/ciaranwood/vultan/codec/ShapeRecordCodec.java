package com.ciaranwood.vultan.codec;

import com.ciaranwood.vultan.types.*;
import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.BitBufferUnderflowException;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;

import java.io.IOException;

public class ShapeRecordCodec implements Codec<ShapeRecord> {

    private final ResolverContext context;
    private final CodecFactory factory;

    public ShapeRecordCodec(ResolverContext context) {
        this.factory = CustomTypes.getCodecFactory(context);
        this.context = context;
    }

    public ShapeRecord decode(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        boolean typeFlag = buffer.readAsBoolean();
        if(typeFlag) {
            return decodeEdgeRecord(buffer, resolver, builder);
        } else {
            return decodeNonEdgeRecord(buffer, resolver, builder);
        }
    }

    private ShapeRecord decodeNonEdgeRecord(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        Codec<ShapeWithStyle.StyleChangeRecord> codec = factory.create(null, ShapeWithStyle.StyleChangeRecord.class, context);
        ShapeWithStyle.StyleChangeRecord record = codec.decode(buffer, resolver, builder);
        if(record.stateNewStyles || record.stateLineStyle || record.stateFillStyle1 || record.stateFillStyle0 || record.stateMoveTo) {
            return record;
        } else {
            //end shape record, throw BitBufferUnderflowException to jump out of the loop
            throw new BitBufferUnderflowException(-1, -1);
        }
    }

    private ShapeRecord decodeEdgeRecord(BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
        boolean straightFlag = buffer.readAsBoolean();
        if(straightFlag) {
            Codec<StraightEdgeRecord> codec = factory.create(null, StraightEdgeRecord.class, context);
            return codec.decode(buffer, resolver, builder);
        } else {
            Codec<CurvedEdgeRecord> codec = factory.create(null, CurvedEdgeRecord.class, context);
            return codec.decode(buffer, resolver, builder);
        }
    }

    public void encode(ShapeRecord value, BitChannel channel, Resolver resolver) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Expression<Integer, Resolver> getSize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CodecDescriptor getCodecDescriptor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class<?>[] getTypes() {
        return new Class<?>[] {EndShapeRecord.class, StyleChangeRecord.class, StraightEdgeRecord.class, CurvedEdgeRecord.class};
    }

    public Class<?> getType() {
        return ShapeRecord.class;
    }

    @Override
    public String toString() {
        return "ShapeRecord codec";
    }
}
