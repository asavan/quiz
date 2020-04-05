public class T1_UnsolvableCase {
    public static void main(String[] args) {
        final Integer i = 1;
        switch (1) {
            case 1: // case i : // not compile
                System.out.println("1");
                break;
            default:
                System.out.println("default");
        }

    }

}
