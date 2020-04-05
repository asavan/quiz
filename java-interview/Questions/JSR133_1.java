package Questions;

public class JSR133_1 {
	/**
	 * How this code will work in (before Java 5 and Java 5+) if:
	 * 1) data and run declared with the volatile keyword
	 * 2) only run declared with the volatile keyword
	 * 3) only data declared with the volatile keyword
	 * 4) no one declared with the volatile keyword
	 */
	static volatile int data = 0;
	static volatile boolean run = true;

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				data = 1;
				run = false;
			}
		}).start();

		while (run) {/*NOP*/};
		System.out.println(data);
	}
}