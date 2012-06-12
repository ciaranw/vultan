package com.ciaranwood.vultan.codec;

import nl.flotsam.pecia.*;
import org.codehaus.preon.*;
import org.codehaus.preon.binding.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.channel.BitChannel;
import org.codehaus.preon.codec.*;
import org.codehaus.preon.el.Expression;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

public class DefaultCodecFactory implements CodecFactory {

    private final List<CodecFactory> addOnFactories;
    private final List<CodecDecorator> addOnDecorators;
    private final List<BindingDecorator> bindingDecorators;
    private final ResolverContext context;


    public DefaultCodecFactory(List<CodecFactory> addOnFactories,
                               List<CodecDecorator> addOnDecorators,
                               List<BindingDecorator> bindingDecorators,
                               ResolverContext context) {
        this.addOnFactories = addOnFactories;
        this.addOnDecorators = addOnDecorators;
        this.bindingDecorators = bindingDecorators;
        this.context = context;
    }

    public <T> Codec<T> create(Class<T> type) {
        return create(null, type, context);
    }

    public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type,
                               ResolverContext context) {

        // The actual cache of Codecs.
        final List<Codec<?>> created = new ArrayList<Codec<?>>();

        // Create the default BindingFactory.
        BindingFactory bindingFactory = new StandardBindingFactory();
        bindingFactory = new ConditionalBindingFactory(bindingFactory);
        bindingFactory = new VersionedBindingFactory(bindingFactory);
        if (bindingDecorators.size() != 0) {
            BindingDecorator[] decoratorsArray = bindingDecorators.toArray(new BindingDecorator[bindingDecorators.size()]);
            bindingFactory = new DecoratingBindingFactory(bindingFactory, decoratorsArray);
        }

        // Eventually, *every* request for a Codec will be processed by this
        // CodecFactory.
        CompoundCodecFactory codecFactory = new CompoundCodecFactory();

        // We need a decorating CodecFactory for all decorators.
        List<CodecDecorator> decorators = new ArrayList<CodecDecorator>();
        decorators.add(new LazyLoadingCodecDecorator());
        decorators.add(new SlicingCodecDecorator());
        decorators.add(new ByteAligningDecorator());
        decorators.add(new InitCodecDecorator());
        decorators.add(new VersionedCodecDecorator());
        decorators.addAll(addOnDecorators);

        DecoratingCodecFactory top = new DecoratingCodecFactory(codecFactory,
                decorators);

        // Add additional CodecFactories passed in.
        for (CodecFactory factory : addOnFactories) {
            codecFactory.add(factory);
        }

        // Add other default CodecFactories.
        codecFactory.add(new ExplicitCodecFactory());
        codecFactory.add(new BoundBufferCodecFactory());
        codecFactory.add(new NumericCodec.Factory());
        codecFactory.add(new StringCodecFactory());
        codecFactory.add(new BooleanCodecFactory());
        codecFactory.add(new EnumCodec.Factory());

        // Create an ObjectCodecFactory that delegates to the
        // CompoundCodecFactory for each of its members.
        ObjectCodecFactory objectCodecFactory = new ObjectCodecFactory(top,
                bindingFactory);

        // Make sure that Codecs created by the ObjectCodecFactory can be
        // cached.
        CachingCodecFactory cache = new CachingCodecFactory(objectCodecFactory,
                new CodecConstructionListener() {

                    public void constructed(Codec<?> codec) {
                        created.add(codec);
                    }

                });

        // Not when you are constructing Lists or arrays of objects.
        codecFactory.add(new ListCodecFactory(top));
        codecFactory.add(new ArrayCodecFactory(top));
        codecFactory.add(new MapCodecFactory(top));

        // Add the (cached) ObjectCodecFactory as a last resort.
        codecFactory.add(cache);
        return new DefaultCodec<T>(top.create(metadata, type, context), created);
    }

    /**
     * The default {@link Codec}.
     *
     * @author Wilfred Springer
     * @param <T>
     */
    private class DefaultCodec<T> implements Codec<T> {

        private Codec<T> delegate;

        private List<Codec<?>> created;

        public DefaultCodec(Codec<T> delegate, List<Codec<?>> created) {
            this.delegate = delegate;
            this.created = created;
        }

        public T decode(BitBuffer buffer, Resolver resolver, Builder builder)
                throws DecodingException {
            return delegate.decode(buffer, resolver, builder);
        }

        public void encode(T value, BitChannel channel, Resolver resolver) throws IOException {
            delegate.encode(value, channel, resolver);
        }

        public Class<?>[] getTypes() {
            return delegate.getTypes();
        }

        public Expression<Integer, Resolver> getSize() {
            return delegate.getSize();
        }

        public Class<?> getType() {
            return delegate.getType();
        }

        public CodecDescriptor getCodecDescriptor() {
            return new CodecDescriptor() {

                public <C extends SimpleContents<?>> Documenter<C> details(
                        final String bufferReference) {
                    return new Documenter<C>() {
                        public void document(C target) {
                            created.remove(delegate);
                            target.document(delegate.getCodecDescriptor()
                                    .details(bufferReference));
                            for (Codec<?> codec : created) {
                                assert codec != null;
                                CodecDescriptor descriptor = codec
                                        .getCodecDescriptor();
                                assert descriptor != null;
                                if (descriptor.requiresDedicatedSection() && target instanceof Contents) {
                                    AnnotatedSection<?> section = ((Contents<?>) target)
                                            .section(descriptor.getTitle());
                                    section
                                            .mark(descriptor.getTitle())
                                            .document(
                                                    descriptor
                                                            .details(bufferReference));
                                    section.end();
                                }
                            }
                        }
                    };
                }

                public String getTitle() {
                    return delegate.getCodecDescriptor().getTitle();
                }

                public <C extends ParaContents<?>> Documenter<C> reference(
                        Adjective adjective, boolean startWithCapital) {
                    return delegate.getCodecDescriptor().reference(adjective, false);
                }

                public boolean requiresDedicatedSection() {
                    return delegate.getCodecDescriptor()
                            .requiresDedicatedSection();
                }

                public <C extends ParaContents<?>> Documenter<C> summary() {
                    return delegate.getCodecDescriptor().summary();
                }

            };
        }

    }

    /**
     * A {@link CodecFactory}, decorating the {@link Codec}s constructed.
     *
     * @author Wilfred Springer
     */
    private static class DecoratingCodecFactory implements CodecFactory {

        /**
         * The {@link CodecFactory} performing the actual work.
         */
        private CodecFactory delegate;

        /**
         * The decorators for decorating the {@link Codec}s constructed.
         */
        private List<CodecDecorator> decorators;

        /**
         * Constructs a new instance.
         *
         * @param delegate   The {@link CodecFactory} to which we need to delegate.
         * @param decorators The {@link CodecDecorator}s that will get a chance to decorate the {@link Codec}
         *                   constructed.
         */
        public DecoratingCodecFactory(CodecFactory delegate,
                                      List<CodecDecorator> decorators) {
            this.delegate = delegate;
            this.decorators = decorators;
        }

        /**
         * {@inheritDoc} Every Codec constructed will be decorated by the {@link #decorators}.
         */
        public <T> Codec<T> create(AnnotatedElement metadata, Class<T> type,
                                   ResolverContext context) {
            Codec<T> codec = delegate.create(metadata, type, context);
            if (codec != null) {
                for (CodecDecorator decorator : decorators) {
                    codec = decorator.decorate(codec, metadata, type, context);
                }
            }
            return codec;
        }

    }

}
