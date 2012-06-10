package com.ciaranwood.vultan.codec;

import org.codehaus.preon.buffer.ByteOrder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SignedNumber {

    String size();

    ByteOrder byteOrder() default ByteOrder.BigEndian;

}
