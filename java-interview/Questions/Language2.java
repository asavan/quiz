package Questions;

public class Language2 {


	private static int getSum(int a, int b) {
		try {
			return a + b;
		} finally {
			a = 10;
			b = 20;
			return a + b;
		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println(getSum(1, new Integer(2)));

		System.out.println(getSum(new Integer(10), 20));
	}
}
