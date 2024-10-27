package meta.depthsum;

import org.junit.Test;

import java.util.List;

import static meta.depthsum.DepthSum.*;
import static org.junit.Assert.assertEquals;


public class DepthSumTest {
    @Test
    public void test() {
        // [4, [5, 6]] == 26
        assertEquals(26, dsum(List.of(new IntItem(4),
                new ArrayItem(List.of(new IntItem(5), new IntItem(6))))));
        // [8, 4, [5, [9], 3], 6]
        assertEquals(61, dsum(List.of(new IntItem(8), new IntItem(4),
                new ArrayItem(List.of(new IntItem(5), new ArrayItem(List.of(new IntItem(9))), new IntItem(3))), new IntItem(6))));
    }
}
