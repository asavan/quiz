import java.math.BigDecimal;
import java.util.Scanner;

public class tink2
{

public static void main(String[] args)
{

    BigDecimal sum = BigDecimal.ZERO;
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLong()) {
        long a = scanner.nextLong();
        sum = sum.add(new BigDecimal(a));
    }

    System.out.print(sum);
}
}