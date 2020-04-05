import java.util.Scanner;

public class Tink_2019_7_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int cand = x;
        int count = 0;
        while (cand <= y) {
            int nextCand = cand * 4;
            if (nextCand > y) {
                break;
            }
            ++count;
            cand = nextCand;
        }
        while (cand <= y) {
            int nextCand = cand + 3;
            if (nextCand > y) {
                break;
            }
            ++count;
            cand = nextCand;
        }
        if (cand == y) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }

}
