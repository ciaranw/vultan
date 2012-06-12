package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.Rect;
import com.ciaranwood.vultan.types.ShapeWithStyle;
import org.codehaus.preon.annotation.*;

@Versioned(1)
public class DefineShape implements DefinitionTag {

    @BoundNumber(size = "16")
    public Integer shapeId;

    @Bound
    @ByteAlign
    public Rect shapeBounds;

    @Bound
    @Versioned(1)
    public ShapeWithStyle shapes;

    public Integer getCharacterId() {
        return shapeId;
    }

    public ShapeWithStyle getShapes() {
        return shapes;
    }
}
