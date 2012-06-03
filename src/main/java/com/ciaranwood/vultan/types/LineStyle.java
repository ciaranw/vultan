package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;

public class LineStyle {

    @BoundNumber(size = "16")
    public Twip width;

    @Bound
    public RGB color;
}
