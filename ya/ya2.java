import java.io.*;
import java.util.Scanner;

public class ya2
{

public static void main(String[] args) throws IOException {
 
	File file = new File("input.txt");
    Scanner scanner = new Scanner(file);

    long a = scanner.nextLong();
	long b = scanner.nextLong();

	try{
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		writer.print(a+b);
		writer.close();
	} catch (IOException e) {
		// do something
	}
}
}