import java.util.Scanner;

public class TT1_Shag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        double up = Math.log(y/x*0.7 + 1);
        double bottom = Math.log(1.7);
        System.out.println((int)Math.ceil(up/bottom));
    }
}
