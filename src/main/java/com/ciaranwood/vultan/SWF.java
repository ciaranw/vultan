package com.ciaranwood.vultan;

import com.ciaranwood.vultan.tags.SWFHeader;

import java.util.Iterator;
import java.util.List;

public class SWF implements Iterable<Object> {

    private final SWFHeader header;
    private final List<Object> tags;

    public SWF(SWFHeader header, List<Object> tags) {
        this.header = header;
        this.tags = tags;
    }

    public SWFHeader getHeader() {
        return header;
    }

    public List<Object> getTags() {
        return tags;
    }

    public Iterator<Object> iterator() {
        return tags.iterator();
    }
}
