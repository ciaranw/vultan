package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.SignedNumber;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.buffer.ByteOrder;

public class StraightEdgeRecord implements ShapeRecord {

    @BoundNumber(size = "4", byteOrder = ByteOrder.BigEndian)
    public Integer numBits;

    @Bound
    public Boolean generalLineFlag;

    @If("generalLineFlag")
    @SignedNumber(size = "numBits + 2")
    public Twip generalDeltaX;

    @If("generalLineFlag")
    @SignedNumber(size = "numBits + 2")
    public Twip generalDeltaY;

    @If("generalLineFlag == false")
    @Bound
    public Boolean vertLineFlag;

    @If("generalLineFlag == false && vertLineFlag == false")
    @SignedNumber(size = "numBits + 2")
    public Twip horizontalDeltaX;

    @If("generalLineFlag == false && vertLineFlag == true")
    @SignedNumber(size = "numBits + 2")
    public Twip verticalDeltaY;

}
