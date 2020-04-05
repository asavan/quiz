package Questions;

import java.io.IOException;

public class Language4 {

	public static void main(String[] args) throws Exception {
		Bar b = new Foo();
		b.doWork();
		b.doAnotherWork();
	}

	static class Bar {
		public void doWork() throws IOException {
			System.out.println("Bar.doWork()");
		}
		public void doAnotherWork() {
			System.out.println("Bar.doAnotherWork()");
		}
	}
	
	static class Foo extends Bar {
		public void doWork() {
			System.out.println("Foo.doWork()");
		}
		
		public void doAnotherWork() throws IllegalArgumentException {
			System.out.println("Foo.doAnotherWork()");
		}
	}
}
