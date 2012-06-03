package com.ciaranwood.vultan.types;

public class Twip {

    private Integer twips;

    public Twip(Integer twips) {
        this.twips = twips;
    }

    public Integer getTwips() {
        return twips;
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
