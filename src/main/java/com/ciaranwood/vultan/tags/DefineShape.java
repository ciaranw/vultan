package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.Rect;
import com.ciaranwood.vultan.types.ShapeWithStyle1;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.ByteAlign;

@Versioned(1)
public class DefineShape implements DefinitionTag {

    @BoundNumber(size = "16")
    public Integer shapeId;

    @Bound
    @ByteAlign
    public Rect shapeBounds;

    @Bound
    @Versioned(1)
    public ShapeWithStyle1 shapes;

    public Integer getCharacterId() {
        return shapeId;
    }

    public ShapeWithStyle1 getShapes() {
        return shapes;
    }
}
