package com.ciaranwood.vultan.tags;

import com.ciaranwood.vultan.types.SymbolEntry;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;

public class SymbolClass {

    @BoundNumber(size = "16")
    public Integer numSymbols;

    @BoundList(size = "numSymbols")
    public SymbolEntry[] symbols;

}
