package Questions;

public class Threads1 {

	public static void main(String... args) throws Exception {
		final Object lock = new Object();

		Thread firstThread = new Thread(() -> {
			synchronized (lock) {
				System.out.print("1");
				try {
					lock.wait();
				} catch (InterruptedException ie) {
					System.out.print("2");
				}
				System.out.print("3");
			}
		});

		firstThread.start();
		Thread.sleep(2000L);

		synchronized (lock) {
			System.out.print("4");
			lock.notify();
			System.out.print("5");
		}
	}
}
