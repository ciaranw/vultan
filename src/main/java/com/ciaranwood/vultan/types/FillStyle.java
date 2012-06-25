package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Versioned;
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
    @Versioned(1)
    public RGB colorv1;

    @If("fillStyleType == FillStyleType.SOLID")
    @Bound
    @Versioned(3)
    public RGBA colorv3;

    @If("fillStyleType == FillStyleType.SOLID")
    @Bound
    @Versioned(4)
    public RGBA colorv4;

    @If("(fillStyleType == FillStyleType.LINEAR_GRADIENT) || " +
        "(fillStyleType == FillStyleType.RADIAL_GRADIENT) || " +
        "(fillStyleType == FillStyleType.FOCAL_RADIAL_GRADIENT)")
    @Bound
    public Matrix gradientMatrix;

    //TODO: Gradient, BitmapId, BitmapMatrix

    public RGB getColor() {
        if(colorv4 != null) {
            return colorv4;
        } else if(colorv3 != null) {
            return colorv3;
        } else {
            return colorv1;
        }
    }


}
