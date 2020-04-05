public class T3_WaitForIt {
    public static void main(String[] args) {
        System.out.println(A.i);
        System.out.println(B.i);
    }
}

class A {
    static {
        i = 2;
    }
    static int i = 1;
};


class B {
    static int i = 1;
    static {
        i = 2;
    }
};

