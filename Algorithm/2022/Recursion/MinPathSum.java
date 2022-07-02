package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-18 08:22:27
 * @Description: 
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class MinPathSum {
    public static int force(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        return process(0, 0, matrix);
    }

    private static int process(int row, int col, int[][] matrix) {
        if (row == matrix.length - 1 && col == matrix[row].length - 1) {
            return matrix[row][col];
        }
        if (row > matrix.length || col > matrix[0].length) {
            return Integer.MAX_VALUE;
        }
        int p1 = process(row + 1, col, matrix);
        int p2 = process(row, col + 1, matrix);
        if (p1 != Integer.MAX_VALUE) {
            p1 += matrix[row][col];
        }
        if (p2 != Integer.MAX_VALUE) {
            p2 += matrix[row][col];
        }
        return Math.min(p1, p2);
    }

    public static int minPath1(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];

        dp[rowLen - 1][colLen - 1] = grid[rowLen - 1][colLen - 1];

        for (int col = colLen - 2; col >= 0; col--) {
            dp[rowLen - 1][col] = grid[rowLen - 1][col] + dp[rowLen - 1][col + 1];
        }

        for (int row = rowLen - 2; row >= 0; row--) {
            dp[row][colLen - 1] = grid[row][colLen - 1] + dp[row + 1][colLen - 1];
        }

        for (int row = rowLen - 2; row >= 0; row--) {
            for (int col = colLen - 2; col >= 0; col--) {
                dp[row][col] = grid[row][col] + Math.min(dp[row + 1][col], dp[row][col + 1]);
            }
        }

        return dp[0][0];
    }

    public static int minPath2(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[] dp = new int[colLen];
        dp[colLen - 1] = grid[rowLen - 1][colLen - 1];

        for (int col = colLen - 2; col >= 0; col--) {
            dp[col] = grid[rowLen - 1][col] + dp[col + 1];
        }

        for (int row = rowLen - 2; row >= 0; row--) {
            dp[colLen - 1] += grid[row][colLen - 1];
            for (int col = colLen - 2; col >= 0; col--) {
                dp[col] = grid[row][col] + Math.min(dp[col + 1], dp[col]);
            }
        }
        return dp[0];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int testTime = 1000;
        System.out.println("start");
        for (int i = 0; i < testTime; i++) {
            int[][] m = generateRandomMatrix(rowSize, colSize);
            int rtn1 = minPath2(m);
            int rtn2 = minPath1(m);
            int rtn3 = force(m);
            if (rtn1 != rtn2 || rtn1 != rtn3) {
                System.out.println("oop");
                break;
            }
        }
        System.out.println("over");
    }
}