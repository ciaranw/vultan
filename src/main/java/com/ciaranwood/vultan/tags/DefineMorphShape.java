package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.Versioned;
import com.ciaranwood.vultan.types.*;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.ByteAlign;
import org.codehaus.preon.buffer.ByteOrder;

@Versioned(1)
public class DefineMorphShape implements DefinitionTag {

    @BoundNumber(size = "16")
    public Integer characterId;

    @Bound
    @ByteAlign
    public Rect startBounds;

    @Bound
    @ByteAlign
    public Rect endBounds;

    @Bound
    @ByteAlign
    @Versioned(2)
    public Rect startEdgeBounds;

    @Bound
    @ByteAlign
    @Versioned(2)
    public Rect endEdgeBounds;

    @BoundNumber(size = "6", byteOrder = ByteOrder.BigEndian)
    @Versioned(2)
    public Integer reserved;

    @Bound
    @Versioned(2)
    public Boolean usesNonScalingStrokes;

    @Bound
    @Versioned(2)
    public Boolean usesScalingStrokes;

    @BoundNumber(size = "32")
    public Integer offset;

    @Bound
    public MorphFillStyleArray morphFillStyles;

    @Bound
    public MorphLineStyleArray morphLineStyles;

    @Bound
    public Shape startEdges;

    @Bound
    public Shape endEdges;


    public Integer getCharacterId() {
        return characterId;
    }
}
