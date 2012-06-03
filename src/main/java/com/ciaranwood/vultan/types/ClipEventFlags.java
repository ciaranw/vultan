package com.ciaranwood.vultan.types;

import com.ciaranwood.vultan.codec.Globals;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.el.ImportStatic;

@ImportStatic(Globals.class)
public class ClipEventFlags {

    @Bound
    public Boolean clipEventKeyUp;

    @Bound
    public Boolean clipEventKeyDown;

    @Bound
    public Boolean clipEventMouseUp;

    @Bound
    public Boolean clipEventMouseDown;

    @Bound
    public Boolean clipEventMouseMove;

    @Bound
    public Boolean clipEventUnload;

    @Bound
    public Boolean clipEventEnterFrame;

    @Bound
    public Boolean clipEventLoad;

    @Bound
    public Boolean clipEventDragOver;

    @Bound
    public Boolean clipEventRollOut;

    @Bound
    public Boolean clipEventRollOver;

    @Bound
    public Boolean clipEventReleaseOutside;

    @Bound
    public Boolean clipEventRelease;

    @Bound
    public Boolean clipEventPress;

    @Bound
    public Boolean clipEventInitialize;

    @Bound
    public Boolean clipEventData;

    @If("Globals.swfVersion >= 6")
    @BoundNumber(size = "5")
    public Integer reserved;

    @If("Globals.swfVersion >= 6")
    @Bound
    public Boolean clipEventConstruct;

    @If("Globals.swfVersion >= 6")
    @Bound
    public Boolean clipEventKeyPress;

    @If("Globals.swfVersion >= 6")
    @Bound
    public Boolean clipEventDragOut;

    @If("Globals.swfVersion >= 6")
    @BoundNumber(size = "8")
    public Integer reservedSwf6;

}
