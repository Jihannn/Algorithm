package Other;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-08 22:42:08
 * 
 * @Description:在N*N的棋盘上放置N个皇后,任意两个皇后不能在同一行/列/斜线上，求有多少种棋面
 */
public class NQueen {
    public static int find(int n) {
        if (n == 0 || n == 2 || n == 3) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // 值表示第n行上皇后在第几列
        int[] queens = new int[n];
        return process(queens, 0, n);
    }

    private static int process(int[] queens, int row, int limit) {
        if (row == limit) {
            return 1;
        }
        int sum = 0;
        // 遍历第row行的0~limit列
        for (int i = 0; i < limit; i++) {
            if (isVaild(queens, row, i)) {
                queens[row] = i;
                sum += process(queens, row + 1, limit);
            }
        }
        return sum;
    }

    private static boolean isVaild(int[] queens, int row, int newCol) {
        for (int lastRow = 0; lastRow < row; lastRow++) {
            // 在同一列或者同一斜线
            if (queens[lastRow] == newCol && Math.abs(queens[lastRow] - newCol) == Math.abs(lastRow - row)) {
                return false;
            }
        }
        return true;
    }
}
