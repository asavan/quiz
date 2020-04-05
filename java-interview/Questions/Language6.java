package Questions;

import static java.lang.System.out;

public class Language6 {

	public static void main(String[] args) {
		int a = 0, b = 0;
		for (int i = 0; i < 10; i++) {
			a = i;
		}

		for (int i = 0; i < 10; ++i) {
			b = i;
		}
		out.println(a);
		out.println(b);
	}
}
