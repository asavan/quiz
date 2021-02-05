package ya.a2;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by asavan on 04.02.2021.
 */
public class TopK {
    public static void main(String[] args) {
        List<Integer> res = topK(Arrays.asList(0, 1, 2, 3, 4), Arrays.asList(4, 2, 0, 3, 1));
        System.out.println(res);
    }

    @Test
    public void test() {
        assertEquals(Arrays.asList(0, 0, 2, 3, 5), topK(Arrays.asList(0, 1, 2, 3, 4), Arrays.asList(4, 2, 0, 3, 1)));
    }

    public static List<Integer> topK(List<Integer> first, List<Integer> second) {
        Map<Integer, Integer> firstMap = new HashMap<>();
        Map<Integer, Integer> secondMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        int N = Math.min(first.size(), second.size());
        result.ensureCapacity(N);
        int same = 0;
        for (int i = 0; i < N; ++i) {
            Integer firstEl = first.get(i);
            Integer secondEl = second.get(i);
            if (firstEl.equals(secondEl)) {
                ++same;
            } else {
                same += getSame(firstMap, secondMap, firstEl);
                same += getSame(secondMap, firstMap, secondEl);
            }
            result.add(same);
        }
        return result;
    }

    private static int getSame(Map<Integer, Integer> firstMap, Map<Integer, Integer> secondMap, Integer firstEl) {
        int same = 0;
        Integer secondPresent = secondMap.get(firstEl);
        if (secondPresent == null || secondPresent.equals(0)) {
            Integer firstPresent = firstMap.get(firstEl);
            if (firstPresent == null) {
                firstMap.put(firstEl, 1);
            } else {
                firstMap.put(firstEl, firstPresent + 1);
            }
        } else {
            secondMap.put(firstEl, secondPresent - 1);
            ++same;
        }
        return same;
    }
}
