public class Despace {

    private char[][] table = {{1, 0, 1},
                              {1, 1, 0},
                              {1, 1, 1}};


    private static int despace(StringBuilder bytes, int howmany) {
        int pos = 0;
        for(int i = 0; i < howmany; ++i) {
            char c = bytes.charAt(i);
            if (c == '\r' || c == '\n' || c == ' ') {
                continue;
            }
            bytes.setCharAt(pos, c);
            ++pos;
        }
        return pos;
    }

    public static void main(String[] args) {
        StringBuilder myName = new StringBuilder("domanokz daad dasda dada 123   ");
        System.out.println(despace(myName, myName.length()));
    }
}
