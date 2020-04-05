package Questions;

public class Language1 {

	interface Drawable {
		void draw();
	}
	
	static abstract class Figure implements Drawable {
		public void draw() {
			System.out.println("Figure ");
		}
	}
	
	static class Triangle extends Figure {
		public void draw() {
			System.out.println("Triangle ");
		}
	}
	
	static class Square extends Figure {
		public void draw() {
			System.out.println("Square ");
		}
	}
	
	public static void main(String[] args) {
		Drawable d;
		Figure f;
		Triangle t = new Triangle();
		Square q = new Square();
		
		d = t;
		f = q;
		
		d.draw();
		f.draw();
	}

}
