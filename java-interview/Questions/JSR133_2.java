package Questions;

import java.util.BitSet;

public class JSR133_2 {
	// What happens if volatile will be dropped ?
	private volatile static BitSet bs = new BitSet(Short.MAX_VALUE);

	public static void main(String[] args) {
		for (short i = 0; i < Short.MAX_VALUE; i++) {
			final short index = i;

			new Thread(() -> {
				bs.set(index, true);
				bs.flip(index);
			}).start();
		}


		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(bs);
	}
}