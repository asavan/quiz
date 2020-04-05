package Questions;

import static java.lang.System.out;


class User {

	private String name;
	private int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
}


public class Language8 {

	public static void main(String[] args) {

		User first = new User("Ivan", 46);
		User second = new User("Ivan", 46);
		User third = new User("Sergei", 23);

		out.println(first == second);
		out.println(second == third);

		out.println(first.equals(second));
		out.println(second.equals(third));

		out.println(first.hashCode() == second.hashCode());
		out.println(second.hashCode() == third.hashCode());
	}
}
