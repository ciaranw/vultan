package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Versioned;
import org.codehaus.preon.annotation.*;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.el.ImportStatic;

@ImportStatic(JoinStyle.class)
public class LineStyle {

    @BoundNumber(size = "16")
    public Twip width;

    @Bound
    @Versioned(1)
    public RGB colorv1;

    @Bound
    @Versioned(3)
    public RGBA colorv3;

    @BoundNumber(size = "2", byteOrder = ByteOrder.BigEndian)
    @Versioned(4)
    public CapStyle startCapStyle;

    @BoundNumber(size = "2", byteOrder = ByteOrder.BigEndian)
    @Versioned(4)
    public JoinStyle joinStyle;

    @Bound
    @Versioned(4)
    public Boolean hasFillFlag;

    @Bound
    @Versioned(4)
    public Boolean noHScaleFlag;

    @Bound
    @Versioned(4)
    public Boolean noVScaleFlag;

    @Bound
    @Versioned(4)
    public Boolean pixelHintingFlag;

    @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
    @Versioned(4)
    public Integer reserved;

    @Bound
    @Versioned(4)
    public Boolean noClose;

    @BoundNumber(size = "2", byteOrder = ByteOrder.BigEndian)
    @Versioned(4)
    public CapStyle endCapStyle;

    @BoundNumber(size = "16")
    @Versioned(4)
    @If("joinStyle == JoinStyle.MITER")
    public Integer miterLimitFactor;  //todo: use fixed 8.8 type

    @Bound
    @Versioned(4)
    @If("hasFillFlag == false")
    public RGBA colorv4;

    @Bound
    @Versioned(4)
    @If("hasFillFlag")
    public FillStyle fillStyle;

    public RGB getColor() {
        if(colorv4 != null) {
            return colorv4;
        } else if(colorv3 != null) {
            return colorv3;
        } else {
            return colorv1;
        }
    }

}
