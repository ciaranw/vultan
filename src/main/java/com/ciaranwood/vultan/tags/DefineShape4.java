package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.Rect;
import com.ciaranwood.vultan.types.ShapeWithStyle;
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
    public ShapeWithStyle.ShapeWithStyle4 shapes;

    @Override
    public ShapeWithStyle getShapes() {
        return shapes;
    }
}
