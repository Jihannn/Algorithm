package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-23 09:43:49
 * @Description: 
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueens {
    public static int nQueen(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2 || n == 3) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    private static int process(int row, int[] record, int n) {
        if (row == n) {
            return 1;
        }
        int ways = 0;
        for (int col = 0; col < n; col++) {
            if (isVaild(row, col, record)) {
                record[row] = col;
                ways += process(row + 1, record, n);
            }
        }
        return ways;
    }

    private static boolean isVaild(int row, int col, int[] record) {
        for (int i = 0; i < row; i++) {
            if (record[i] == col || Math.abs(row - i) == Math.abs(record[i] - col)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 12;
        System.out.println(nQueen(n));
    }
}
