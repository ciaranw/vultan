package com.ciaranwood.vultan.codec;

import org.codehaus.preon.CodecDecorator;
import org.codehaus.preon.CodecFactory;
import org.codehaus.preon.ResolverContext;
import org.codehaus.preon.binding.BindingDecorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultCodecFactoryBuilder {
    
    private final List<CodecFactory> additionalFactories;
    private final List<CodecDecorator> additionalCodecDecorators;
    private final List<BindingDecorator> additionalBindingDecorators;
    private ResolverContext context;

    public DefaultCodecFactoryBuilder() {
        this.additionalFactories = new ArrayList<CodecFactory>();
        this.additionalCodecDecorators = new ArrayList<CodecDecorator>();
        this.additionalBindingDecorators = new ArrayList<BindingDecorator>();
    }
    
    public DefaultCodecFactoryBuilder codecFactories(CodecFactory... factories) {
        Collections.addAll(additionalFactories, factories);
        return this;
    }
    
    public DefaultCodecFactoryBuilder codecDecorators(CodecDecorator... decorators) {
        Collections.addAll(additionalCodecDecorators, decorators);
        return this;
    }
    
    public DefaultCodecFactoryBuilder bindingDecorators(BindingDecorator... decorators) {
        Collections.addAll(additionalBindingDecorators, decorators);
        return this;
    }
    
    public DefaultCodecFactoryBuilder context(ResolverContext context) {
        this.context = context;
        return this;
    }
    
    public DefaultCodecFactory build() {
        return new DefaultCodecFactory(additionalFactories, additionalCodecDecorators, additionalBindingDecorators, context);
    }
}
