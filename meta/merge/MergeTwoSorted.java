package meta.merge;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSorted {

    public record Interval(int begin, int end) {}

    static class Iter {
        private List<Interval> a;
        private int ind;
        public Iter(List<Interval> a) {
            this.a = a;
            ind = 0;
        }
        public void swap(Iter other) {
            List<Interval> temp = a;
            a = other.a;
            other.a = temp;
            int tempInd = ind;
            ind = other.ind;
            other.ind = tempInd;
        }
        public boolean less(Iter other) {
            if (!hasNext()) {
                return false;
            }
            if (!other.hasNext()) {
                return true;
            }
            return getCurrent().begin <= other.getCurrent().begin;
        }

        public boolean hasNext() {
            return ind < a.size();
        }
        Interval getCurrent() {
            return a.get(ind);
        }
        public Interval next() {
            Interval temp = getCurrent();
            ++ind;
            return temp;
        }
    }

    public static List<Interval> merge(List<Interval> a, List<Interval> b) {
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }
        List<Interval> result = new ArrayList<>();
        Iter it = new Iter(a);
        Iter it2 = new Iter(b);
        if (!it.less(it2)) {
            it.swap(it2);
        }
        Interval cur = it.next();
        while (it.hasNext() || it2.hasNext()) {
            if (!it.less(it2)) {
                it.swap(it2);
            }
            if (cur.end < it.getCurrent().begin) {
                result.add(cur);
                cur = it.next();
            } else {
                cur = new Interval(cur.begin, Math.max(cur.end, it.getCurrent().end));
                it.next();
            }
        }
        result.add(cur);
        return result;
    }
}
