package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Versioned;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.el.ImportStatic;

@ImportStatic(FillStyleType.class)
public class MorphFillStyle {

    @BoundNumber(size = "8")
    public FillStyleType fillStyleType;

    @If("fillStyleType == FillStyleType.SOLID")
    @Bound
    public RGBA startColor;

    @If("fillStyleType == FillStyleType.SOLID")
    @Bound
    public RGBA endColor;

    @If("(fillStyleType == FillStyleType.LINEAR_GRADIENT) || " +
            "(fillStyleType == FillStyleType.RADIAL_GRADIENT) || " +
            "(fillStyleType == FillStyleType.FOCAL_RADIAL_GRADIENT)")
    @Bound
    public Matrix startGradientMatrix;

    @If("(fillStyleType == FillStyleType.LINEAR_GRADIENT) || " +
            "(fillStyleType == FillStyleType.RADIAL_GRADIENT) || " +
            "(fillStyleType == FillStyleType.FOCAL_RADIAL_GRADIENT)")
    @Bound
    public Matrix endGradientMatrix;

    //TODO: Gradient, BitmapId, StartBitmapMatrix, EndBitmapMatrix

}
