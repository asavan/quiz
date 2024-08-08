package ya;

import java.util.Comparator;
import java.util.PriorityQueue;

class MergeKLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode result = new ListNode();
        ListNode cur = result;
        var map = new PriorityQueue<ListNode>(lists.length, Comparator.comparingInt(p -> p.val));
        for (var l : lists) {
            if (l != null) {
                map.add(l);
            }
        }
        while (!map.isEmpty()) {
            var l = map.poll();
            cur.next = l;
            cur = l;
            if (cur.next != null) {
                map.add(cur.next);
            }
        }
        return result.next;
    }
}
