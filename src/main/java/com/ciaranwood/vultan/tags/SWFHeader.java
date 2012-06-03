package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Fixed;
import com.ciaranwood.vultan.types.Rect;
import org.codehaus.preon.annotation.*;

import java.math.BigDecimal;

public class SWFHeader {

    @BoundString(size = "3", match = "FWS")
    public String signature;

    @BoundNumber(size = "8")
    public Integer version;

    @Bound
    public Integer fileLength;

    @Bound
    @ByteAlign
    public Rect frameSize;

    @Bound
    @Fixed(size = "16")
    public BigDecimal frameRate;

    @BoundNumber(size = "16")
    public Integer frameCount;

}
