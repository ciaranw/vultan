package com.ciaranwood.vultan;

import com.ciaranwood.vultan.tags.PlaceObject2;
import com.ciaranwood.vultan.types.Matrix;
import org.codehaus.preon.DecodingException;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MotionTweenTest {

    @Test
    public void testMotionTween() throws IOException, DecodingException {
        ByteBuffer data = SwfReader.readFromClasspath("test-motion-tween.swf");
        SwfParser parser = new SwfParser(data);

        SWF swf = parser.parse();

        List<Object> tags = swf.getTags();

        assertEquals(13, tags.size());

        PlaceObject2 placeObject = (PlaceObject2) tags.get(5);
        assertTrue(placeObject.placeFlagHasMatrix);

        Matrix matrix = placeObject.matrix;

        assertEquals(new BigDecimal("56.25"), matrix.translateX.getPixels());
        assertEquals(new BigDecimal("250.05"), matrix.translateY.getPixels());

    }
}
