package com.ciaranwood.vultan.codec;

import org.codehaus.preon.buffer.AbstractBitBufferDecorator;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.BitBufferUnderflowException;
import org.codehaus.preon.buffer.ByteOrder;

import java.nio.ByteBuffer;

public class ReadZeroLengthBitBuffer extends AbstractBitBufferDecorator {

    private final BitBuffer delegate;

    public ReadZeroLengthBitBuffer(BitBuffer delegate) {
        this.delegate = delegate;
    }

    @Override
    public int readAsInt(int nrBits) {
        return readAsInt(nrBits, ByteOrder.BigEndian);
    }

    @Override
    public int readAsInt(long bitPos, int nrBits) {
        return readAsInt(bitPos, nrBits, ByteOrder.BigEndian);
    }

    @Override
    public int readAsInt(int nrBits, ByteOrder endian) {
        if(nrBits == 0) {
            return 0;
        } else {
            return super.readAsInt(nrBits, endian);
        }
    }

    @Override
    public int readAsInt(long bitPos, int nrBits, ByteOrder endian) {
        if(nrBits == 0) {
            return 0;
        } else {
            return super.readAsInt(bitPos, nrBits, endian);
        }
    }

    @Override
    public BitBuffer getDelegate() {
        return delegate;
    }

    public ByteBuffer readAsByteBuffer(int length) throws BitBufferUnderflowException {
        return delegate.readAsByteBuffer(length);
    }

    public ByteBuffer readAsByteBuffer() {
        return delegate.readAsByteBuffer();
    }

    public long getActualBitPos() {
        return delegate.getActualBitPos();
    }
}
