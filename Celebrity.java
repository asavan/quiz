/**
 * Created by asavan on 12.02.2019.
 */
public class Celebrity {

    static int findCelebrity(int[][] matrix) {
        int candidate = 0;
        int n = matrix.length;
        for (int i = 1; i < n; ++i) {
            if (matrix[i][candidate] != 1) {
                candidate = i;
            }
        }
        for (int i = 1; i < n; ++i) {
            if (matrix[i][candidate] != 1) {
                return -1;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        {
            int[][] matrix = {
                    {1, 1, 1, 0},
                    {0, 1, 1, 1},
                    {0, 0, 1, 0},
                    {0, 0, 1, 1}
            };

            System.out.println(findCelebrity(matrix));
        }


        {
            int[][] matrix = {
                    {1, 1, 1, 0},
                    {0, 1, 1, 1},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1}
            };

            System.out.println(findCelebrity(matrix));
        }

        {
            int[][] matrix = {
                    {1, 1, 1, 0},
                    {0, 1, 1, 1},
                    {1, 0, 1, 0},
                    {0, 0, 1, 1}
            };

            System.out.println(findCelebrity(matrix));
        }



    }
}
