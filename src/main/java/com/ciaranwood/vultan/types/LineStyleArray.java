package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.buffer.ByteOrder;

public class LineStyleArray {

    @BoundNumber(size = "8", byteOrder = ByteOrder.BigEndian)
    public Integer lineStyleCount;

    @If("lineStyleCount == 0xFF")
    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    public Integer lineStyleCountExtended;

    @If("lineStyleCount < 0xFF")
    @BoundList(size = "lineStyleCount")
    public LineStyle[] lineStyles;

    @If("lineStyleCount == 0xFF")
    @BoundList(size = "lineStyleCountExtended")
    public LineStyle[] lineStylesExtended;
}
