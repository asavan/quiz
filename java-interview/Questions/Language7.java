package Questions;

import static java.lang.System.out;

class Super {
	static {
		out.println("Questions.Super static initialized");
	}
}

class One {
	static {
		out.println("Questions.One static initialized");
	}

	public One() {
		out.println("Questions.One dynamic initialized");
	}
}

class Two extends Super {
	static {
		out.println("Questions.Two static initialized");
	}
}

class Three extends Two{
	static {
		out.println("Questions.Three static initialized");
	}

	static One one = new One();

	public static One getOne() {
		return one;
	}
}

public class Language7 {

	public static void main(String[] args) throws Exception {
		One o = null;
		Two t = new Two();
		out.println((Object) o == (Object) t);

		Three.getOne();
	}

}
