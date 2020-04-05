import java.util.Scanner;

public class T3_Card {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int h = scanner.nextInt();
        int w = scanner.nextInt();
        int H = scanner.nextInt();
        int W = scanner.nextInt();
        int t = Math.max(H, W);
        H = Math.min(H, W);
        W = t;
        t = Math.max(h, w);
        h = Math.min(h, w);
        w = t;
        int d2 = w * w + h * h;
        if ((h <= H && w <= W) || H * Math.sqrt(d2 - W * W) + W * Math.sqrt(d2 - H * H) <= w * w - h * h) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
