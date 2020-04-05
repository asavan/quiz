import java.util.*;

import static java.util.Collections.sort;

public class TT6_Pin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] matr = new char[n][m];
        for (int i = 0; i < n; ++i) {
            String inp = scanner.next();
            for (int j = 0; j < m; ++j) {
                matr[i][j] = inp.charAt(j);
            }
        }
        int L = scanner.nextInt();
        List<String> set = new ArrayList<>();
        formSet(matr, set, 0, 0, n, m, Character.toString(matr[0][0]));
        sort(set);
        int k = 1;
        for (String s : set) {
            if (k == L) {
                System.out.println(s);
                return;
            }
            ++k;
        }
    }

    private static void formSet(char[][] matr, Collection<String> set, int i, int j, int N, int M, String str) {
        if (i == N - 1 && j == M - 1) {
            set.add(str);
            return;
        }
        if (i < N - 1) {
            formSet(matr, set, i + 1, j, N, M, str + matr[i + 1][j]);
        }
        if (j < M - 1) {
            formSet(matr, set, i, j + 1, N, M, str + matr[i][j + 1]);
        }
    }
}
