package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.SignedNumber;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

public class Rect {

    @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
    public Integer numBits;

    @SignedNumber(size = "numBits")
    public Twip xMin;

    @SignedNumber(size = "numBits")
    public Twip xMax;

    @SignedNumber(size = "numBits")
    public Twip yMin;

    @SignedNumber(size = "numBits")
    public Twip yMax;
}
