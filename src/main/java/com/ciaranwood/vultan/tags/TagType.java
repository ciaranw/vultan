package com.ciaranwood.vultan.tags;

import org.codehaus.preon.annotation.BoundEnumOption;

public enum TagType {
    Unknown(-1, null),
    End(0, End.class),
    ShowFrame(1, ShowFrame.class),
    DefineShape(2, DefineShape.class),
    SetBackgroundColor(9, SetBackgroundColor.class),
    DefineShape2(22, DefineShape.class),
    PlaceObject2(26, PlaceObject2.class),
    DefineSprite(39, DefineSprite.class),
    FileAttributes(69, FileAttributes.class),
    SymbolClass(76, SymbolClass.class);

    private Integer code;
    private Class<?> type;

    private TagType(Integer code, Class<?> type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public Class<?> getType() {
        return type;
    }

    public static TagType getByCode(Integer code) {
        for(TagType type : values()) {
            if(type.getCode().equals(code)) {
                return type;
            }
        }

        throw new IllegalArgumentException("unknown TagType with code " + code);
    }

}
