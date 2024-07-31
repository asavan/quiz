import java.util.*;

/**
 * Created by asavan on 02.12.2016.
 */

// T next();
// boolean hasNext();
// remove();

class NewIterator<T> {
    private Iterator<? extends T> one;
    private Iterator<? extends T> two;
    private boolean isFirst = true;

    public NewIterator(Iterator<? extends T> one, Iterator<? extends T> two) {
        this.one = one;
        this.two = two;
    }

    T next() {
        if (one.hasNext()) {
            return one.next();
        }
        isFirst = false;
        return two.next();
    }

    boolean hasNext() {
        return one.hasNext() || two.hasNext();
    }

    void remove() {
        if (isFirst) {
            one.remove();
            return;
        }
        two.remove();
    }


    // ["eat", "tea", "tan", "ate", "nat", "bat"]
/* [
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
] */

    private static Collection<Collection<String>> anagrams(List<String> strs) {
        Map<Print, Collection<String>> map = new LinkedHashMap<>();
        for (String str : strs) {
            Print p = new Print(str);
            map.computeIfAbsent(p, k -> new ArrayList<>()).add(str);
        }

        return map.values();
    }

    private static class Print {
        private static final int ALPHABET_SIZE = 26;
        private int[] array;

        Print(String word) {
            array = new int[ALPHABET_SIZE];
            for (char c : word.toCharArray()) {
                array[c - 'a'] += 1;
            }
        }

        @Override
        public int hashCode() {
            int acc = 0;
            int i = 1;
            for (int c : array) {
                acc += c * i;
                ++i;
            }
            return acc;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (!(other instanceof Print)) return false;
            for (int i = 0; i < ALPHABET_SIZE; ++i) {
                if (this.array[i] != ((Print) other).array[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] str) {
        String[] testArr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Collection<Collection<String>> lists = NewIterator.anagrams(Arrays.asList(testArr));
        System.out.print(lists);
    }
}
