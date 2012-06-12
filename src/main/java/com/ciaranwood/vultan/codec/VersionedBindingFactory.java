package com.ciaranwood.vultan.codec;

import nl.flotsam.pecia.Documenter;
import nl.flotsam.pecia.ParaContents;
import nl.flotsam.pecia.SimpleContents;
import org.codehaus.preon.*;
import org.codehaus.preon.binding.Binding;
import org.codehaus.preon.binding.BindingFactory;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.el.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

public class VersionedBindingFactory implements BindingFactory {

    private final BindingFactory factory;

    public VersionedBindingFactory(BindingFactory factory) {
        this.factory = factory;
    }

    public Binding create(AnnotatedElement metadata, Field field, Codec<?> codec, ResolverContext context, Documenter<ParaContents<?>> containerReference) {
        Binding binding = factory.create(metadata, field, codec, context, containerReference);
        if(metadata != null && metadata.isAnnotationPresent(Versioned.class)) {
            Versioned annotation = metadata.getAnnotation(Versioned.class);
            return new VersionedBinding(binding, annotation.value(), field);
        } else {
            return binding;
        }
    }

    private static class VersionedBinding implements Binding {

        private final Binding binding;
        private final Integer version;
        private final Field field;
        
        private static final Logger log = LoggerFactory.getLogger(VersionedBinding.class);

        private VersionedBinding(Binding binding, Integer version, Field field) {
            this.binding = binding;
            this.version = version;
            this.field = field;
        }

        public void load(Object object, BitBuffer buffer, Resolver resolver, Builder builder) throws DecodingException {
            log.debug("trying to load into {} for {}", object, field);
            if(version.equals(VersionStack.INSTANCE.getCurrentVersion())) {
                log.debug("loaded - version was {}", version);
                binding.load(object, buffer, resolver, builder);
            }
        }

        public <V extends SimpleContents<?>> V describe(V contents) {
            return binding.describe(contents);
        }

        public <T, V extends ParaContents<T>> V writeReference(V contents) {
            return binding.writeReference(contents);
        }

        public Class<?>[] getTypes() {
            return binding.getTypes();
        }

        public Object get(Object context) throws IllegalArgumentException, IllegalAccessException {
            return binding.get(context);
        }

        public String getName() {
            return binding.getName();
        }

        public Expression<Integer, Resolver> getSize() {
            return binding.getSize();
        }

        public String getId() {
            return binding.getId();
        }

        public Class<?> getType() {
            return binding.getType();
        }

        public void save(Object value, BitChannel channel, Resolver resolver) throws IOException {
            binding.save(value, channel, resolver);
        }
    }
}
