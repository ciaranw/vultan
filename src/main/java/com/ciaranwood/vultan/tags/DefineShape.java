package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.types.Rect;
import com.ciaranwood.vultan.types.ShapeWithStyle;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.ByteAlign;

public class DefineShape {

    @BoundNumber(size = "16")
    public Integer shapeId;

    @Bound
    @ByteAlign
    public Rect shapeBounds;

    @Bound
    public ShapeWithStyle shapes;
}
