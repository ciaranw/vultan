package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.BoundNumber;

public class RGBA extends RGB {

    @BoundNumber(size = "8")
    public Integer alpha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RGBA rgba = (RGBA) o;

        if (!alpha.equals(rgba.alpha)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + alpha.hashCode();
        return result;
    }
}
