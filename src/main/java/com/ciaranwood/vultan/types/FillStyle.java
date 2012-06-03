package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.el.ImportStatic;

@ImportStatic(FillStyleType.class)
public class FillStyle {

    @BoundNumber(size = "8")
    public FillStyleType fillStyleType;

    @If("fillStyleType == FillStyleType.SOLID")
    @Bound
    public RGB color;

    @If("(fillStyleType == FillStyleType.LINEAR_GRADIENT) || " +
        "(fillStyleType == FillStyleType.RADIAL_GRADIENT) || " +
        "(fillStyleType == FillStyleType.FOCAL_RADIAL_GRADIENT)")
    @Bound
    public Matrix gradientMatrix;


}
