package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.buffer.ByteOrder;

public class FillStyleArray {

    @BoundNumber(size = "8")
    public Integer fillStyleCount;

    @If("fillStyleCount == 0xFF")
    @BoundNumber(size = "16")
    public Integer fillStyleCountExtended;

    @If("fillStyleCount < 0xFF")
    @BoundList(size = "fillStyleCount")
    public FillStyle[] fillStyles;

    @If("fillStyleCount == 0xFF")
    @BoundList(size = "fillStyleCountExtended")
    public FillStyle[] fillStylesExtended;
}
