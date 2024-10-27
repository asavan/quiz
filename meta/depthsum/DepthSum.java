package meta.depthsum;

import java.util.List;

public class DepthSum {
    public sealed interface Item permits ArrayItem, IntItem {};
    public record ArrayItem(List<Item> items) implements Item {}
    public record IntItem(int val) implements Item {}
    public static int dsum(List<Item> items) {
        return sumrec(items, 1);
    }

    private static int sumrec(List<Item> items, int d) {
        int sum = 0;
        for (Item it: items) {
            if (it instanceof ArrayItem its) {
                sum += sumrec(its.items, d + 1);
            } else if (it instanceof IntItem iti){
                sum += iti.val * d;
            }
        }
        return sum;
    }
}
