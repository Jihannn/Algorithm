package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-18 10:27:37
 * @Description: 
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {
    public static Double live(int N, int M, int row, int col, int k) {
        if (k < 0 || row < 0 || row > N || col < 0 || col > M) {
            return -1.0;
        }
        return process(row, col, k, N, M) / Math.pow(4, k);
    }

    private static int process(int row, int col, int k, int N, int M) {
        if (!(row > 0 && row <= N && col > 0 && col <= M)) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        int p1 = process(row + 1, col, k - 1, N, M);
        int p2 = process(row - 1, col, k - 1, N, M);
        int p3 = process(row, col + 1, k - 1, N, M);
        int p4 = process(row, col - 1, k - 1, N, M);
        return p1 + p2 + p3 + p4;
    }

    public static Double liveDP(int N, int M, int row, int col, int k) {
        if (k < 0 || row < 0 || row > N || col < 0 || col > M) {
            return -1.0;
        }
        int[][][] dp = new int[N][M][k + 1];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                dp[r][c][0] = 1;
            }
        }
        for (int level = 1; level <= k; level++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    int p1 = r - 1 > 0 ? dp[r - 1][c][level - 1] : 0;
                    int p2 = r + 1 < N ? dp[r + 1][c][level - 1] : 0;
                    int p3 = c - 1 > 0 ? dp[r][c - 1][level - 1] : 0;
                    int p4 = c + 1 < M ? dp[r][c + 1][level - 1] : 0;
                    dp[r][c][level] = p1 + p2 + p3 + p4;
                }
            }
        }
        return dp[row][col][k] / Math.pow(4, k);
    }

    public static void main(String[] args) {
        int N = 10;
        int M = 10;
        int row = 2;
        int col = 2;
        int k = 10;
        System.out.println(live(N, M, row, col, k));
        System.out.println(liveDP(N, M, row, col, k));
    }
}
