package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.ReadZeroLength;
import com.ciaranwood.vultan.codec.SignedNumber;
import com.ciaranwood.vultan.codec.StartByteAligned;
import org.codehaus.preon.annotation.*;
import org.codehaus.preon.buffer.ByteOrder;

import java.util.List;

public class ShapeWithStyle {

    @Bound
    public FillStyleArray fillStyles;

    @Bound
    public LineStyleArray lineStyleArray;

    @BoundNumber(size = "4", byteOrder = ByteOrder.BigEndian)
    public Integer numFillBits;

    @BoundNumber(size = "4", byteOrder = ByteOrder.BigEndian)
    public Integer numLineBits;

    @BoundList(type = ShapeRecord.class)
    @ByteAlign
    public List<ShapeRecord> shapeRecords;

    public class StyleChangeRecord implements ShapeRecord {

        @Bound
        public Boolean stateNewStyles;

        @Bound
        public Boolean stateLineStyle;

        @Bound
        public Boolean stateFillStyle1;

        @Bound
        public Boolean stateFillStyle0;

        @Bound
        public Boolean stateMoveTo;

        @If("stateMoveTo")
        @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
        public Integer moveBits;

        @If("stateMoveTo")
        @SignedNumber(size = "moveBits")
        public Twip moveDeltaX;

        @If("stateMoveTo")
        @SignedNumber(size = "moveBits")
        public Twip moveDeltaY;

        @If("stateFillStyle0")
        @ReadZeroLength
        @BoundNumber(size = "outer.numFillBits", byteOrder = ByteOrder.BigEndian)
        public Integer fillStyle0;

        @If("stateFillStyle1")
        @ReadZeroLength
        @BoundNumber(size = "outer.numFillBits", byteOrder = ByteOrder.BigEndian)
        public Integer fillStyle1;

        @If("stateLineStyle")
        @ReadZeroLength
        @BoundNumber(size = "outer.numLineBits", byteOrder = ByteOrder.BigEndian)
        public Integer lineStyle;

        @If("stateNewStyles")
        @StartByteAligned
        @Bound
        public FillStyleArray fillStyles;

        @If("stateNewStyles")
        @Bound
        public LineStyleArray lineStyles;

        @If("stateNewStyles")
        @BoundNumber(size = "4", byteOrder = ByteOrder.BigEndian)
        public Integer numFillBits;

        @If("stateNewStyles")
        @BoundNumber(size = "4", byteOrder = ByteOrder.BigEndian)
        public Integer numLineBits;

        @Init
        public void init() {
            if(stateNewStyles) {
                ShapeWithStyle.this.numFillBits = numFillBits;
                ShapeWithStyle.this.numLineBits = numLineBits;
            }
        }

    }

}
