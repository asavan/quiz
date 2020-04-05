
public class Main33333 {

    public static void main(String[] args) {

        {
            String s = "one@live@not on evil@two";
            String b = transform(s);
            System.out.println(b);
        }

        {
            String s = "@@not on @";
            String b = transform(s);
            System.out.println(b);
        }

    }

    private static String transform(String s) {
        StringBuilder b = new StringBuilder(s);
        int firstIndex = -1;
        int lastIndex = -1;
        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            if (c == '@') {
                if (firstIndex < 0) {
                    firstIndex = i;
                } else {
                    lastIndex = i;
                }
            }
        }
        firstIndex += 1;
        lastIndex -= 1;
        int mid = (lastIndex - firstIndex) / 2;
        System.out.println(b.charAt(firstIndex + mid));
        System.out.println(mid);
        for (int i = 0; i <= mid; ++i) {
            char f = b.charAt(firstIndex + i);
            char l = b.charAt(lastIndex - i);
            b.setCharAt(firstIndex + i, l);
            b.setCharAt(lastIndex - i, f);
        }
        return b.toString();
    }
}