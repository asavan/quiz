package Questions;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Collections1 {

	public static void main(String[] args) {
		List<Integer> items = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			items.add(i);
		}

		for (Integer item : items) {
			if (item % 2 == 0) {
				items.remove(item);
			}
		}
		out.println(items);
	}
}
