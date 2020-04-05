import java.util.*;

public class Puzzle {

    public static void main(String[] args) {
        String st1 = "test1";
        String st2 = "test2";
        String st3 = "test3";
        String st4 = "test4";
        String st5 = "test4";

        List<String> testList = new ArrayList<>();
        Set<String> testSet = new LinkedHashSet<>();
        testList.add(st1);
        testList.add(st2);
        testList.add(st3);
        testList.add(st4);
        testList.add(st5);

        testSet.add(st1);
        testSet.add(st2);
        testSet.add(st3);
        testSet.add(st4);
        testSet.add(st5);

        System.out.println(testList);
        System.out.println(testSet);

        st1 = "test111";

        System.out.println(testList);
        System.out.println(testSet);
    }

}
