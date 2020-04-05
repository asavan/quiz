package Questions;

public class Language3 {

	static class MyException extends Exception {
		private static final long serialVersionUID = 5578323166887791678L;

	}


	public static void main(String[] args) throws Exception {
		try {

			if (args.length > 7)
				throw new MyException();

		} catch (MyException me) {
			System.out.println("catch...");
			return;
		} finally {
			System.out.println("finally...");
		}
	}
}
