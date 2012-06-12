package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.ShapeWithStyle;
import org.codehaus.preon.annotation.Bound;

@Versioned(3)
public class DefineShape3 extends DefineShape {

    @Bound
    @Versioned(3)
    public ShapeWithStyle.ShapeWithStyle3 shapes;

    @Override
    public ShapeWithStyle getShapes() {
        return shapes;
    }
}
