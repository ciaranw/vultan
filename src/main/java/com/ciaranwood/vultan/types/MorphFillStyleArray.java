package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;

public class MorphFillStyleArray {

    @BoundNumber(size = "8")
    public Integer fillStyleCount;

    @If("fillStyleCount == 0xFF")
    @BoundNumber(size = "16")
    public Integer fillStyleCountExtended;

    @If("fillStyleCount < 0xFF")
    @BoundList(size = "fillStyleCount")
    public MorphFillStyle[] fillStyles;

    @If("fillStyleCount == 0xFF")
    @BoundList(size = "fillStyleCountExtended")
    public MorphFillStyle[] fillStylesExtended;

}
