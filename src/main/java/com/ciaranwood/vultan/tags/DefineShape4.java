package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.Rect;
import com.ciaranwood.vultan.types.ShapeWithStyle1;
import com.ciaranwood.vultan.types.ShapeWithStyle4;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.ByteAlign;

@Versioned(4)
public class DefineShape4 extends DefineShape {

    @Bound
    @ByteAlign
    public Rect edgeBounds;

    @BoundNumber(size = "5")
    public Integer reserved;

    @Bound
    public Boolean usesFillWindingRule;

    @Bound
    public Boolean usesNonScalingStrokes;

    @Bound
    public Boolean usesScalingStrokes;

    @Bound
    @Versioned(4)
    public ShapeWithStyle4 shapes;

    @Override
    public ShapeWithStyle1 getShapes() {
        return shapes;
    }
}
