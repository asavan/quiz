import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int peterSum = 0;
        int vasyaSum = 0;
        Scanner keyboard = new Scanner(System.in);
        int q = keyboard.nextInt();
        for (int i = 0; i < q/3; ++i) {
            int p = keyboard.nextInt();
            int v = keyboard.nextInt();
            int s = keyboard.nextInt();
            vasyaSum += v;
            peterSum += p;
            if (p > v) {
                vasyaSum += s;
            } else {
                peterSum += s;
            }
        }
        String answer = peterSum > vasyaSum ? "Petya" : "Vasya";
        System.out.print(answer);
    }
}
