package Questions;

import static java.lang.System.out;

class Data {
	String question;
	int answer;
	int maxAllowedValue;

	public Data() {
		this.answer = 42;
		this.question = "Ultimate Question of Life, the Universe, and Everything";
		this.maxAllowedValue = 9000;
	}
}


class DataProvider {

	private Data data;

	public Data getData() {
		if (data == null) {
			synchronized (Data.class) {
				if (data == null) {
					data = new Data();
					out.println("Questions.Data initialized!");
				}
			}
		}
		return data;
	}
}


public class Threads4 {

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		DataProvider provider = new DataProvider();

		for (int i = 0; i < 100; ++i) {
			new Thread(() -> {
				Data data = provider.getData();
				out.println(data.question);
			}).start();
		}

		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		out.println(totalTime);
	}

}
