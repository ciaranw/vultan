package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Versioned;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.el.ImportStatic;

@ImportStatic(JoinStyle.class)
public class MorphLineStyle {

    @BoundNumber(size = "16")
    public Twip startWidth;

    @BoundNumber(size = "16")
    public Twip endWidth;

    @BoundNumber(size = "2", byteOrder = ByteOrder.BigEndian)
    @Versioned(2)
    public CapStyle startCapStyle;

    @BoundNumber(size = "2", byteOrder = ByteOrder.BigEndian)
    @Versioned(2)
    public JoinStyle joinStyle;

    @Bound
    @Versioned(2)
    public Boolean hasFillFlag;

    @Bound
    @Versioned(2)
    public Boolean noHScaleFlag;

    @Bound
    @Versioned(2)
    public Boolean noVScaleFlag;

    @Bound
    @Versioned(2)
    public Boolean pixelHintingFlag;

    @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
    @Versioned(2)
    public Integer reserved;

    @Bound
    @Versioned(2)
    public Boolean noClose;

    @BoundNumber(size = "2", byteOrder = ByteOrder.BigEndian)
    @Versioned(2)
    public CapStyle endCapStyle;

    @BoundNumber(size = "16")
    @Versioned(2)
    @If("joinStyle == JoinStyle.MITER")
    public Integer miterLimitFactor;  //todo: use fixed 8.8 type

    @Bound
    public RGBA startColor;

    @Bound
    public RGBA endColor;

}
