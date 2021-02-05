package ya.a2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asavan on 05.02.2021.
 */
public class SubarraySum {
    static class SubArray {
        public int x;
        public int y;

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }
    public static void main(String[] args) {
        int [] input = {1, 2, 3, 0, 4, 5};
        {
            SubArray res = subarraySum(input, 7);
            System.out.println(res);
        }

        {
            SubArray res = subarraySum(input, 1);
            System.out.println(res);
        }
    }

    private static SubArray subarraySum(int[] input, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int partialSum = 0;
        map.put(partialSum, -1);
        int index = 0;
        for (int i : input) {
            partialSum += i;
            map.put(partialSum, index);
            ++index;
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int left = entry.getKey();
            Integer right = map.get(target + left);
            if (right != null && right > entry.getValue()) {
                SubArray sub = new SubArray();
                sub.x = entry.getValue() + 1;
                sub.y = right;
                return sub;
            }
        }
        return null;
    }
}
