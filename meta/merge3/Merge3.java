package meta.merge3;

import java.util.ArrayList;
import java.util.List;

public class Merge3 {
    class Holder {
        private int ind;
        private List<Integer> a;
        public Holder(List<Integer> a) {
            this.a = a;
            ind = 0;
        }
        public boolean hasNext() {
            return ind < a.size();
        }
        public int size() {
            return a.size();
        }

        public int getCurrent() {
            return a.get(ind);
        }
        int next() {
            int tmp = getCurrent();
            ++ind;
            return tmp;
        }
    }
    List<Integer> merge(List<Integer> a, List<Integer> b, List<Integer> c) {
        var holders = List.of(new Holder(a), new Holder(b), new Holder(c));
        List<Integer> result = new ArrayList<>(holders.stream().mapToInt(Holder::size).sum());
        while (holders.stream().anyMatch(Holder::hasNext)) {
            Holder min = holders.getFirst();
            for (int i = 1; i < holders.size(); ++i) {
                if (!min.hasNext()) {
                    min = holders.get(i);
                } else if (holders.get(i).hasNext()) {
                    if (holders.get(i).getCurrent() < min.getCurrent()) {
                        min = holders.get(i);
                    }
                }
            }
            int val = min.next();
            if (result.getLast() != val) {
                result.add(val);
            }
        }
        return result;
    }
}
