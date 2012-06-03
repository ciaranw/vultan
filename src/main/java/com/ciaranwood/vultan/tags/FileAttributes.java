package com.ciaranwood.vultan.tags;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;

public class FileAttributes {

    @Bound
    public Boolean reserved;

    @Bound
    public Boolean useDirectBlit;

    @Bound
    public Boolean useGPU;

    @Bound
    public Boolean hasMetadata;

    @Bound
    public Boolean actionscript3;

    @BoundNumber(size = "2")
    public Integer reserved2;

    @Bound
    public Boolean useNetwork;

    @BoundNumber(size = "24")
    public Integer reserved3;
}
