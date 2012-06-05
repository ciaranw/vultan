package com.ciaranwood.vultan.types;

import java.math.BigDecimal;

public class Twip {

    private Integer twips;

    private static final BigDecimal DIVISOR = new BigDecimal("20");

    public Twip(Integer twips) {
        this.twips = twips;
    }

    public Integer getTwips() {
        return twips;
    }

    public BigDecimal getPixels() {
        BigDecimal inTwips = new BigDecimal(twips);
        return inTwips.divide(DIVISOR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Twip twip = (Twip) o;

        if (!twips.equals(twip.twips)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return twips.hashCode();
    }

    @Override
    public String toString() {
        return "Twip(" + twips +')';
    }
}
