package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.tags.TagType;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;

public class RecordHeader {

    private final TagType tagType;
    private final Integer length;

    public RecordHeader(TagType tagType, Integer length) {
        this.tagType = tagType;
        this.length = length;
    }

    public TagType getTagType() {
        return tagType;
    }

    public Integer getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "RecordHeader{" +
                "tagType=" + tagType +
                ", length=" + length +
                '}';
    }
}
