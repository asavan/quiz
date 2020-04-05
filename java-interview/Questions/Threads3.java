package Questions;

import static java.lang.System.out;

public class Threads3 {

	public static void main(String[] args) throws Exception {

		final Object r1 = new Object();
		final Object r2 = new Object();

		new Thread(() -> {
			synchronized (r1) {
				out.println("Locked resource #1");
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				out.println("Waiting for resource #2");
				synchronized (r2) {

				}
			}
		}).start();

		new Thread(() -> {
			synchronized (r2) {
				out.println("Locked resource #2");

				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				out.println("Waiting for resource #1");
				synchronized (r1) {

				}
			}
		}).start();
	}

}
