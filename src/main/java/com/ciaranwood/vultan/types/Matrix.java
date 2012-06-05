package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Fixed;
import com.ciaranwood.vultan.codec.SignedNumber;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.buffer.ByteOrder;

import java.math.BigDecimal;

public class Matrix {

    @Bound
    public Boolean hasScale;

    @If("hasScale")
    @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
    public Integer nScaleBits;

    @If("hasScale")
    @Fixed(size = "nScaleBits", lowBitSize = "16")
    public BigDecimal scaleX;

    @If("hasScale")
    @Fixed(size = "nScaleBits", lowBitSize = "16")
    public BigDecimal scaleY;

    @Bound
    public Boolean hasRotate;

    @If("hasRotate")
    @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
    public Integer nRotateBits;

    @If("hasRotate")
    @Fixed(size = "nRotateBits", lowBitSize = "16")
    public BigDecimal rotateSkew0;

    @If("hasRotate")
    @Fixed(size = "nRotateBits", lowBitSize = "16")
    public BigDecimal rotateSkew1;

    @BoundNumber(size = "5", byteOrder = ByteOrder.BigEndian)
    public Integer nTranslateBits;

    @SignedNumber(size = "nTranslateBits")
    public Twip translateX;

    @SignedNumber(size = "nTranslateBits")
    public Twip translateY;

}
