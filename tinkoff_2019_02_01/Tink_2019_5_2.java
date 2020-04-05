import java.util.*;

public class Tink_2019_5_2 {
    static class Segm implements Comparable<Segm> {
        public int beg;
        public int end;

        @Override
        public int compareTo(Segm o) {
            return beg - o.beg;
        }

        public Segm(int i, int j) {
            beg = i;
            end = j;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Segm> segmList = new ArrayList<>(m);
        for (int i = 0; i < m; ++i) {
            int beg = scanner.nextInt();
            int end = scanner.nextInt();
            Segm s = new Segm(beg, end);
            int index = Collections.binarySearch(segmList, s);
            // -(insertion point) - 1 === x
            // in = -x - 1
            if (index < 0) {
                index = -index - 1;
            }
            while (true) {
                if (segmList.isEmpty()) {
                    break;
                }
                if (index > segmList.size() - 1) {
                    break;
                }
                Segm oldSegm = segmList.get(index);
                if (!intersect(oldSegm, s)) {
                    break;
                }
                segmList.remove(index);
            }
            segmList.add(index, s);
        }
        System.out.print(segmList.size());
    }

    private static boolean intersect(Segm oldSegm, Segm s) {
        return oldSegm.end >= s.beg && oldSegm.beg <= s.end;
    }
}
