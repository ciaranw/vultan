package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.*;
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
