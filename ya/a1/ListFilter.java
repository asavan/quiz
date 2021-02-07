package ya.a1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asavan on 07.02.2021.
 */
public class ListFilter {
    public static void main(String[] args) {
        int[] first = {0, 0, 0, 1, 1, 2, 3, 5, 6, 7};
        int[] second = {0, 0, 2, 4, 5};
        List<Integer> result = listFilter(first, second);
        System.out.println(result);
    }

    private static List<Integer> listFilter(int[] first, int[] second) {
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                result.add(first[i]);
                ++i;
            } else if (first[i] > second[j]) {
                ++j;
            } else {
                ++i;
                ++j;
            }
        }
        for (; i < first.length; ++i) {
            result.add(first[i]);
        }
        return result;
    }
}
