package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Versioned;
import org.codehaus.preon.annotation.Bound;

@Versioned(1)
public class ShapeWithStyle1 {

    @Bound
    public FillStyleArray fillStyles;

    @Bound
    public LineStyleArray lineStyleArray;

    @Bound
    public Shape shape;
}
