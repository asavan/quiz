import java.util.Scanner;

public class T1_Pizza {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        String peopleStr = scanner.nextLine();
        printPizza(phrase, peopleStr);
    }

    private static void printPizza(String phrase, String peopleStr) {
        int count = calcSpace(phrase);
        String[] peoples = peopleStr.split(" ");
        int index = count % peoples.length;
        System.out.println(peoples[index]);
    }

    private static int calcSpace(String s) {
        int spaces = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                ++spaces;
            }
        }
        return spaces;
    }
}
