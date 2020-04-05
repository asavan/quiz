import java.util.Scanner;

public class Main
{

public static void main(String[] args)
{

    Scanner scanner = new Scanner(System.in);

    long a = scanner.nextLong();
	long b = scanner.nextLong();

	int sum = 0;
	for (int i = 1; i <= 145; i+=8) {
	    sum += i;
    }



    System.out.print(sum);
}
}