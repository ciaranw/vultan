package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;

public class SymbolEntry {

    @BoundNumber(size = "16")
    public Integer characterId;

    @BoundString(encoding = "UTF-8")
    public String name;

    @Override
    public String toString() {
        return "SymbolEntry{" +
                characterId +
                ":" + name +
                '}';
    }
}
