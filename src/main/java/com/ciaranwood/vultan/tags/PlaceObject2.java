package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.types.CXFormWithAlpha;
import com.ciaranwood.vultan.types.Matrix;
import org.codehaus.preon.annotation.*;

public class PlaceObject2 {

    @Bound
    public Boolean placeFlagHasClipActions;

    @Bound
    public Boolean placeFlagHasClipDepth;

    @Bound
    public Boolean placeFlagHasName;

    @Bound
    public Boolean placeFlagHasRatio;

    @Bound
    public Boolean placeFlagHasColorTransform;

    @Bound
    public Boolean placeFlagHasMatrix;

    @Bound
    public Boolean placeFlagHasCharacter;

    @Bound
    public Boolean placeFlagMove;

    @BoundNumber(size = "16")
    public Integer depth;

    @If("placeFlagHasCharacter")
    @BoundNumber(size = "16")
    public Integer characterId;

    @If("placeFlagHasMatrix")
    @Bound
    @ByteAlign
    public Matrix matrix;

    @If("placeFlagHasColorTransform")
    @Bound
    @ByteAlign
    public CXFormWithAlpha colorTransform;

    @If("placeFlagHasRatio")
    @BoundNumber(size = "16")
    public Integer ratio;

    @If("placeFlagHasName")
    @BoundString
    public String name;

    @If("placeFlagHasClipDepth")
    @BoundNumber(size = "16")
    public Integer clipDepth;

    @If("placeFlagHasClipActions")
    public Object clipActions;

    public boolean isAddNewCharacter() {
        return !placeFlagMove && placeFlagHasCharacter;
    }

    public boolean isModifyExistingCharacter() {
        return placeFlagMove && !placeFlagHasCharacter;
    }

    public boolean isReplaceCharacter() {
        return placeFlagMove && placeFlagHasCharacter;
    }
}
