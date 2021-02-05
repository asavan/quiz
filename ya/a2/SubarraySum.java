package ya.a2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by asavan on 05.02.2021.
 */
public class SubarraySum {
    static class SubArray {
        public int x;
        public int y;

        public SubArray(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubArray subArray = (SubArray) o;
            return x == subArray.x && y == subArray.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
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

    @Test
    public void test() {
        {
            int[] arr = {1, 4, 20, 3, 10, 5};
            assertEquals(new SubArray(2, 4), subarraySum(arr, 33));
        }

        {
            int [] arr = {10, 2, -2, -20, 10};
            assertEquals(new SubArray(0, 3), subarraySum(arr, -10));
        }
        {
            int [] arr = {-10, 0, 2, -2, -20, 10};
            assertNull(subarraySum(arr, 20));
        }
        {
            int [] arr =  {1, 2, 3, 0, 4, 5};
            assertEquals(new SubArray(2, 4), subarraySum(arr, 7));
            assertEquals(new SubArray(0, 0), subarraySum(arr, 1));
        }
    }

    private static SubArray subarraySum(int[] input, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int partialSum = 0;
        map.put(partialSum, -1);
        int index = 0;
        for (int i : input) {
            partialSum += i;
            Integer left = map.get(partialSum - target);
            if (left != null) {
                return new SubArray(left + 1, index);
            }
            map.put(partialSum, index);
            ++index;
        }
        return null;
    }
}
