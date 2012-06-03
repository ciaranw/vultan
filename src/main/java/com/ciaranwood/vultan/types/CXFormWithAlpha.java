package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;

public class CXFormWithAlpha {
    
    @Bound
    public Boolean hasAddTerms;
    
    @Bound
    public Boolean hasMultTerms;
    
    @BoundNumber(size = "4")
    public Integer nBits;
    
    @If("hasMultTerms")
    @BoundNumber(size = "nBits")
    public Integer redMultTerm;

    @If("hasMultTerms")
    @BoundNumber(size = "nBits")
    public Integer greenMultTerm;
    
    @If("hasMultTerms")
    @BoundNumber(size = "nBits")
    public Integer blueMultTerm;

    @If("hasMultTerms")
    @BoundNumber(size = "nBits")
    public Integer alphaMultTerm;

    @If("hasAddTerms")
    @BoundNumber(size = "nBits")
    public Integer redAddTerm;

    @If("hasAddTerms")
    @BoundNumber(size = "nBits")
    public Integer greenAddTerm;

    @If("hasAddTerms")
    @BoundNumber(size = "nBits")
    public Integer blueAddTerm;

    @If("hasAddTerms")
    @BoundNumber(size = "nBits")
    public Integer alphaAddTerm;
}
