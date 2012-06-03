package com.ciaranwood.vultan.codec;

import org.codehaus.preon.Resolver;
import org.codehaus.preon.el.BindingException;

import java.util.Map;

public class StaticResolver implements Resolver {

    private final Map<String, Object> values;

    public StaticResolver(Map<String, Object> values) {
        this.values = values;
    }

    public Object get(String name) throws BindingException {
        if (values.containsKey(name)) {
            return values.get(name);
        } else {
            return null;
        }
    }

    public Resolver getOriginalResolver() {
        return this;
    }
}
