package Questions;

import static java.lang.System.out;

public class Language9 {

	private static void functionA(boolean... a) {
		out.println(a);
	}

	private static void functionB(Boolean... a) {
		out.println(a);
	}

	public static void main(String[] args) {
		functionA(false, true);
		functionB(true, false);
	}
}
