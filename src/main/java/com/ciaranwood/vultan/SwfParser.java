package com.ciaranwood.vultan;

import com.ciaranwood.vultan.codec.*;
import com.ciaranwood.vultan.tags.SWFHeader;
import org.apache.commons.io.IOUtils;
import org.codehaus.preon.*;
import org.codehaus.preon.buffer.BitBuffer;
import org.codehaus.preon.buffer.DefaultBitBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class SwfParser {
    
    private final ByteBuffer data;

    public SwfParser(ByteBuffer data) {
        this.data = data;
    }
    
    public SWF parse() throws DecodingException {
        CodecFactory factory = CustomTypes.getCodecFactory(null);

        BitBuffer buffer;

        byte[] signature = new byte[3];
        byte[] compressed = new byte[] {0x43, 0x57, 0x53};

        data.get(signature);

        data.rewind();

        if(Arrays.equals(signature, compressed)) {
            buffer = uncompress(data);
        } else {
            buffer = new DefaultBitBuffer(data);
        }

        SWFHeader header = new SwfHeaderParser(factory, buffer).parse();

        Globals.swfVersion = header.version;

        List<Object> tags = new TagCodec(factory).decode(buffer, null, new DefaultBuilder());

        return new SWF(header, tags);
    }

    private BitBuffer uncompress(ByteBuffer data) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] nonCompressed = new byte[8];
            data.get(nonCompressed);
            nonCompressed[0] = 0x46;

            output.write(nonCompressed);
            ByteBuffer compressedData = data.slice();

            byte[] compressedBytes = new byte[compressedData.remaining()];
            compressedData.get(compressedBytes);

            Inflater inflater = new Inflater();
            inflater.setInput(compressedBytes);

            byte[] buffer = new byte[1024];

            int count = 0;

            try {
                do {
                    count = inflater.inflate(buffer);
                    output.write(buffer, 0, count);
                } while (count != 0 && !inflater.finished());
            } catch (DataFormatException dfe) {
                dfe.printStackTrace();
            }

            return new DefaultBitBuffer(ByteBuffer.wrap(output.toByteArray()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(output);
        }
    }
}
