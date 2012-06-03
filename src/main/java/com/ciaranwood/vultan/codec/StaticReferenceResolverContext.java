package com.ciaranwood.vultan.codec;

import org.codehaus.preon.Resolver;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.el.BindingException;
import org.codehaus.preon.el.Document;
import org.codehaus.preon.el.Expression;
import org.codehaus.preon.el.Reference;

import java.util.HashMap;
import java.util.Map;

public class StaticReferenceResolverContext implements ResolverContext {

    private final ResolverContext delegate;
    private final Map<String, Object> references;

    public StaticReferenceResolverContext(ResolverContext delegate) {
        this.delegate = delegate;
        this.references = new HashMap<String, Object>();
    }

    public void addReference(String name, Object value) {
        references.put(name, value);
    }

    public Reference<Resolver> selectAttribute(String name) throws BindingException {
        if(references.containsKey(name)) {
            return new LiteralReference<Resolver>(references.get(name), this);
        } else {
            return delegate.selectAttribute(name);
        }
    }

    public Reference<Resolver> selectItem(String index) throws BindingException {
        return delegate.selectItem(index);
    }

    public Reference<Resolver> selectItem(Expression<Integer, Resolver> index) throws BindingException {
        return delegate.selectItem(index);
    }

    public void document(Document target) {
        delegate.document(target);
    }

    public Resolver getResolver() {
        return new StaticResolver(references);
    }

}
