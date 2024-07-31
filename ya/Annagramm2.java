package ya;

import java.util.*;

public class Annagramm2 {
    static class Trace {
        private final String normal;
        public Trace(String s) {
            var chars = s.toCharArray();
            Arrays.sort(chars);
            normal = new String(chars);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Trace trace = (Trace) o;
            return Objects.equals(normal, trace.normal);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(normal);
        }
    }

    public static void main(String[] args) {
        String[] testArr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Collection<Collection<String>> lists = anagrams(Arrays.asList(testArr));
        System.out.print(lists);
    }

    private static Collection<Collection<String>> anagrams(List<String> list) {
        Map<Trace, Collection<String>> map = new LinkedHashMap<>();
        for (var word : list) {
            var tr = new Trace(word);
            map.computeIfAbsent(tr, _k -> new TreeSet<>()).add(word);
        }
        return map.values();
    }
}
