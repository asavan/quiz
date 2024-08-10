package ya;

public class PathsInMatrix {
    private static final int MODULO = (int)1e9 + 7;

    int getDiff(int value, int k) {
        return value % k;
    }

    int[] newArr(int[] prev, int diff) {
        int k = prev.length;
        int[] cur = new int[k];
        for (int i = 0; i < k; ++i) {
            cur[(i+diff)%k] = prev[i];
        }
        return cur;
    }

    void newArr2(int[] cur, int[] prev, int diff) {
        int k = prev.length;
        for (int i = 0; i < k; ++i) {
            cur[(i+diff)%k] = prev[i];
        }
    }

    int[] mergeArr(int[] l1, int[] l2) {
        int k = l1.length;
        int[] cur = new int[k];
        for (int i = 0; i < k; ++i) {
            cur[i] = (l1[i] + l2[i]) % MODULO;
        }
        return cur;
    }

    void mergeArr2(int[] cur, int[] l1, int[] l2) {
        int k = l1.length;
        for (int i = 0; i < k; ++i) {
            cur[i] = (l1[i] + l2[i]) % MODULO;
        }
    }

    void fillFirst(int[][] l1, int n, int[][] grid, int k) {
        int[] prev = new int[k];
        prev[0] = 1;
        newArr2(l1[0], prev, getDiff(grid[0][0], k));
        for (int i = 1; i < n; ++i) {
            newArr2(l1[i], l1[i-1], getDiff(grid[0][i], k));
        }
    }
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        var levelPrev = new int[n][k];
        var levelCur = new int[n][k];
        fillFirst(levelPrev, n, grid, k);
        for (int i = 1; i < m; ++i) {
            levelCur[0] = newArr(levelPrev[0], getDiff(grid[i][0], k));
            for (int in = 1; in < n; ++in) {
                int diff = getDiff(grid[i][in], k);
                levelCur[in] = mergeArr(newArr(levelCur[in-1], diff), newArr(levelPrev[in], diff));
            }
            levelPrev = levelCur;
        }
        return levelPrev[n-1][0];
    }
}
