package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.codec.TagCodecFactory;
import org.codehaus.preon.annotation.BoundExplicitly;
import org.codehaus.preon.annotation.BoundNumber;

import java.util.List;

public class DefineSprite {

    @BoundNumber(size = "16")
    public Integer spriteId;

    @BoundNumber(size = "16")
    public Integer frameCount;

    @BoundExplicitly(factory = TagCodecFactory.class)
    public List<Object> tags;
}
