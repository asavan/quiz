import java.util.*;

public class T4_Pair<T> {

    private final T first;
    private final T second;

    public T4_Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first), String.valueOf(second));
    }

    public static void main(String[] args) {
        T4_Pair<Object> p = new T4_Pair<Object>(23, "skidoo");
        System.out.print(p.first + " " + p.second);
        for (String s : p.stringList()) {
            System.out.print(s + " ");
        }
    }

}
