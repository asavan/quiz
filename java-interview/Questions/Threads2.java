package Questions;

public class Threads2 {

	public static void main(String[] args) throws Exception {
		final Object lock = new Object();

		Runnable templateThread = () -> {
			synchronized (lock) {
				System.out.print("First");
				try {
					lock.wait();
				} catch (InterruptedException ie) {
					System.out.print("Second");
				}
				System.out.print("Third");
			}
		};

		for (int i = 0; i < 3; i++) {
			new Thread(templateThread).start();
		}

		Thread.sleep(2000L);

		synchronized (lock) {
			lock.notifyAll();
		}
	}

}
