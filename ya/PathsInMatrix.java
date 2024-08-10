package ya;

// https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/

public class PathsInMatrix {
    private static final int MODULO = (int) 1e9 + 7;

    void newArr2(int[] cur, int[] prev, int diff) {
        int k = prev.length;
        for (int i = 0; i < k; ++i) {
            cur[(i + diff) % k] = prev[i];
        }
    }

    void mergeArr2(int[] cur, int[] l1, int[] l2) {
        int k = l1.length;
        for (int i = 0; i < k; ++i) {
            cur[i] = (l1[i] + l2[i]) % MODULO;
        }
    }

    int[][] fillLine(int[][] levelPrev, int n, int[][] grid, int k, int line) {
        var levelCur = new int[n][k];
        newArr2(levelCur[0], levelPrev[0], grid[line][0]);
        for (int in = 1; in < n; ++in) {
            int diff = grid[line][in];
            newArr2(levelCur[in], levelCur[in - 1], diff);
            newArr2(levelPrev[in - 1], levelPrev[in], diff);
            mergeArr2(levelCur[in], levelCur[in], levelPrev[in - 1]);
        }
        return levelCur;
    }

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        var level = new int[n][k];
        level[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            level = fillLine(level, n, grid, k, i);
        }
        return level[n - 1][0];
    }
}
