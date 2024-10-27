package meta.i18n;

public class PatternMatcher {
    sealed interface Matcher permits IntMatcher, LetterMatcher{
        int skip(String s, int from);
    }
    static final class IntMatcher implements Matcher {
        public IntMatcher(int n) {
            this.n = n;
        }
        @Override
        public int skip(String s, int from) {
            if (from + n > s.length()) {
                return -1;
            }
            return n;
        }
        private final int n;
    }
    static final class LetterMatcher implements Matcher {
        private final char c;

        public LetterMatcher(char c) {
            this.c = c;
        }

        @Override
        public int skip(String s, int from) {
            if (s.charAt(from) != c) {
                return -1;
            }
            return 1;
        }
    };
    static class Pattern {
        public Pattern(String p) {
            pattern = p;
            ind = 0;
        }
        public boolean hasNext() {
            return ind < pattern.length();
        }
        public Matcher next() {
            char c = pattern.charAt(ind);
            int res = 0;
            while (c >= '0' && c <= '9') {
                res *= 10;
                res += c - '0';
                ++ind;
                if (hasNext()) {
                    c = pattern.charAt(ind);
                } else {
                    break;
                }
            }
            if (res > 0) {
                return new IntMatcher(res);
            }
            ++ind;
            return new LetterMatcher(c);
        }
        private final String pattern;
        private int ind;
    }
    public static boolean match(String s, String pattern) {
        Pattern p = new Pattern(pattern);
        int ind = 0;
        while (p.hasNext()) {
            Matcher m = p.next();
            int toSkip = m.skip(s, ind);
            if (toSkip < 0) {
                return false;
            }
            ind += toSkip;
        }
        return ind == s.length();
    }
}
