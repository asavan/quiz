import java.math.BigDecimal;
import java.util.Scanner;

public class TinkSeptember6 {

    public static void main1(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigDecimal[][] memory = new BigDecimal[1001][1001];
        fillMemory(memory);
        System.out.println(grafBize(n, memory));
    }

    public static void main(String[] args) {
        BigDecimal[][] memory = new BigDecimal[1001][1001];
        long b = System.currentTimeMillis();
        fillMemory(memory);
        for (int i = 1000; i <= 1000; ++i) {
            BigDecimal obj = grafBize(i, memory);
            long e = System.currentTimeMillis();
            System.out.print(obj);
            System.out.print(" ");
            System.out.println(e-b);
        }
    }


    private static BigDecimal grafBize(int n, BigDecimal[][] memory) {
        return helper(0, n, memory);
    }

    private static void fillMemory(BigDecimal[][] memory) {
        for (int i = 0; i < 1001; ++i) {
            for (int j = 0; j < 1001; ++j) {
                memory[i][j] = new BigDecimal(-1);
            }
        }
    }

    private static BigDecimal helper(int prevLevel, int rest, BigDecimal[][] memory) {
        if (prevLevel >= rest) return BigDecimal.ZERO;
        BigDecimal prevValue = memory[prevLevel][rest];
        if (prevValue.compareTo(BigDecimal.ZERO) >= 0) {
            return prevValue;
        }
        BigDecimal sum = BigDecimal.ONE;
        for (int i = prevLevel + 1; i < rest; ++i) {
            BigDecimal res = helper(i, rest - i, memory);
            if (res.compareTo(BigDecimal.ZERO) == 0) break;
            sum = sum.add(res);
        }
        memory[prevLevel][rest] = sum;
        return sum;
    }

}