package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.ShapeWithStyle1;
import com.ciaranwood.vultan.types.ShapeWithStyle3;
import org.codehaus.preon.annotation.Bound;

@Versioned(3)
public class DefineShape3 extends DefineShape {

    @Bound
    @Versioned(3)
    public ShapeWithStyle3 shapes;

    @Override
    public ShapeWithStyle1 getShapes() {
        return shapes;
    }
}
