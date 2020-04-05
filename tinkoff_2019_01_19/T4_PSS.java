import java.util.Scanner;

public class T4_PSS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int sum = count(s, 0, 0);
        System.out.println(sum);
    }

    public static void main1(String[] args) {
        String s = "??????????????????????????????????";
        System.out.println(s.length());
        int sum = count("??????????????????????????????????", 0, 0);
        System.out.println(sum);
    }

    private static int count(String s, int index, int counter) {
        if (counter < 0) {
            return 0;
        }
        if (s.length() - index < counter) {
            return 0;
        }
        int sum = 0;
        for (int i = index; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                counter++;
            } else if (s.charAt(i) == ')') {
                counter--;
            } else if (s.charAt(i) == '?') {
                sum += count(s, i + 1, counter - 1);
                sum += count(s, i + 1, counter + 1);
                return sum;
            }
            if (counter < 0) {
                return 0;
            }
        }
        if (counter == 0) {
            ++sum;
        }
        return sum;
    }
}
