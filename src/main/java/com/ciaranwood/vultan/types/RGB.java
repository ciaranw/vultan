package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.BoundNumber;

public class RGB {
    
    @BoundNumber(size = "8")
    public Integer red;
    
    @BoundNumber(size = "8")
    public Integer green;
    
    @BoundNumber(size = "8")
    public Integer blue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RGB rgb = (RGB) o;

        if (!blue.equals(rgb.blue)) return false;
        if (!green.equals(rgb.green)) return false;
        if (!red.equals(rgb.red)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = red.hashCode();
        result = 31 * result + green.hashCode();
        result = 31 * result + blue.hashCode();
        return result;
    }
}
