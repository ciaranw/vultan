package com.ciaranwood.vultan.types;

import org.codehaus.preon.annotation.BoundEnumOption;

public enum FillStyleType {

    @BoundEnumOption(0x00) SOLID,
    @BoundEnumOption(0x10) LINEAR_GRADIENT,
    @BoundEnumOption(0x12) RADIAL_GRADIENT,
    @BoundEnumOption(0x13) FOCAL_RADIAL_GRADIENT,
    @BoundEnumOption(0x40) REPEATING_BITMAP,
    @BoundEnumOption(0x41) CLIPPED_BITMAP,
    @BoundEnumOption(0x42) NON_SMOOTHED_REPEATING_BITMAP,
    @BoundEnumOption(0x43) NON_SMOOTHED_CLIPPED_BITMAP
}
