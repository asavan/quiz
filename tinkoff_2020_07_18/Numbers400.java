package tinkoff_2020_07_18;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asavan on 13.07.2020.
 */
public class Numbers400 {

    static private ArrayList<ArrayList<Integer>> getCompositions(int n) {
        ArrayList<ArrayList<Integer>> listOfCompositions = new ArrayList<>();
        ArrayList<Integer> composition = new ArrayList<>();
        composition.add(n);
        while (composition != null) {
            listOfCompositions.add(composition);
            composition = getComposition(composition, n);
        }
        return listOfCompositions;
    }

    private static ArrayList<Integer> getComposition(ArrayList<Integer> previousComposition, int n) {
        ArrayList<Integer> currentComposition = new ArrayList<>(previousComposition);
        for (int i = currentComposition.size() - 1; i >= 0; i--) {
            if (currentComposition.get(i) != 1) {
                currentComposition.set(i, currentComposition.get(i) - 1);
                if (currentComposition.size() > i + 1) {
                    if (((currentComposition.size() - (i + 1)) > 1)) {
                        int sumOfOnes = 0;
                        for (int j = currentComposition.size() - 1; j >= i + 1; j--) {
                            sumOfOnes += currentComposition.get(j);
                            if (j != i + 1) currentComposition.remove(j);
                        }
                        currentComposition.set(i + 1, sumOfOnes + 1);
                    } else currentComposition.set(i + 1, currentComposition.get(i + 1) + 1);
                } else currentComposition.add(1);
                return currentComposition;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> a = getCompositions(9);
        int count = 0;
        for (ArrayList<Integer> integers : a) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println("  ");
            count += integers.size();
        }
        System.out.println(count);
    }
}
