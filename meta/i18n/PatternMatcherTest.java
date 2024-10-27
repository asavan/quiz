package meta.i18n;

import org.junit.Test;

import static meta.i18n.PatternMatcher.match;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PatternMatcherTest {
    @Test
    public void testGood() {
        assertTrue(match("internationalization", "i18n"));
        assertTrue(match("interpolation", "i11n"));
        assertTrue(match("facebook", "f6k"));
        assertTrue(match("Facebook", "F2eb2k"));
    }

    @Test
    public void testBad() {
        assertFalse(match("interpolation", "i18n"));
        assertFalse(match("focus", "f6k"));
    }


    @Test
    public void testEmpty() {
        assertTrue(match("", ""));
        assertFalse(match("", "1"));
        assertFalse(match("1", ""));
        assertTrue(match("1", "1"));
        assertFalse(match("1", "0"));
    }
}
