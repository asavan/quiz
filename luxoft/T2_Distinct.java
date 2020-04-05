import java.util.HashSet;
import java.util.Set;

public class T2_Distinct {

    public static <T extends Set> T distinct(T t) {
        return t;
    }

    public static void main(String[] args) {
        HashSet set = distinct(new HashSet());
        // ArrayDeque set1 = distinct(new ArrayDeque());
        Set set2 = distinct(new HashSet());
        // Set set3 = distinct(new ArrayDeque());
        // Set set4 = distinct(new Set());

    }

}
