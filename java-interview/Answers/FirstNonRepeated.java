package Answers;

import java.util.HashMap;
import java.util.Scanner;

public class FirstNonRepeated {

	public static void main(String[] args) {
		System.out.println(" Please enter the input string :");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		char c = firstNonRepeatedCharacter(s);
		System.out.println("The first non repeated character is :  " + c);
	}

	public static Character firstNonRepeatedCharacter(String str) {
		HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
		int i, length;
		Character c;
		length = str.length();
		for (i = 0; i < length; i++) {
			c = str.charAt(i);
			if (characterIntegerHashMap.containsKey(c)) {
				characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) + 1);
			} else {
				characterIntegerHashMap.put(c, 1);
			}
		}

		for (i = 0; i < length; i++) {
			c = str.charAt(i);
			if (characterIntegerHashMap.get(c) == 1)
				return c;
		}
		return null;
	}
}