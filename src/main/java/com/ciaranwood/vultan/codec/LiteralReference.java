package com.ciaranwood.vultan.codec;

import org.codehaus.preon.el.*;

public class LiteralReference<E> implements Reference<E> {

    private Object value;
    private ReferenceContext<E> context;

    public LiteralReference(Object value, ReferenceContext<E> context) {
        this.value = value;
        this.context = context;
    }

    public Object resolve(E context) {
        return value;
    }

    public ReferenceContext<E> getReferenceContext() {
        return context;
    }

    public boolean isAssignableTo(Class<?> type) {
        return type.isAssignableFrom(value.getClass());
    }

    public Class<?> getType() {
        return value.getClass();
    }

    public Reference<E> narrow(Class<?> type) {
        return this;
    }

    public boolean isBasedOn(ReferenceContext<E> eReferenceContext) {
        return false;
    }

    public Reference<E> rescope(ReferenceContext<E> eReferenceContext) {
        return this;
    }

    public Reference<E> selectAttribute(String name) throws BindingException {
        throw new BindingException("No such attribute");
    }

    public Reference<E> selectItem(String index) throws BindingException {
        throw new BindingException("No such indexed value");
    }

    public Reference<E> selectItem(Expression<Integer, E> index) throws BindingException {
        throw new BindingException("No such indexed value");
    }

    public void document(Document target) {
        target.text(value.toString());
    }
}
