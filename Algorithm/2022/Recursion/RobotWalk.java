package Recursion;

/*
 * @Author: Jihan
 * @Date: 2022-05-13 08:36:09
 * @Description: 
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 */
public class RobotWalk {
    // M:当前位置,K:必须走K步,N:有N个数,P:目标位置
    public static int robotWalk1(int M, int K, int N, int P) {
        if (N < 2 || M < 1 || M > N || P > N || P < 1) {
            return -1;
        }
        return process1(M, K, N, P);
    }

    private static int process1(int M, int K, int N, int P) {
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        if (M == 1) {
            return process1(M + 1, K - 1, N, P);
        }
        if (M == N) {
            return process1(M - 1, K - 1, N, P);
        }
        return process1(M + 1, K - 1, N, P) + process1(M - 1, K - 1, N, P);
    }

    public static int robotWalk2(int M, int K, int N, int P) {
        if (N < 2 || M < 1 || M > N || P > N || P < 1) {
            return -1;
        }
        int[][] cache = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                cache[i][j] = -1;
            }
        }
        return process2(M, K, N, P, cache);
    }

    private static int process2(int M, int K, int N, int P, int[][] cache) {
        if (cache[M][K] != -1) {
            return cache[M][K];
        }
        int result = -1;
        if (K == 0) {
            result = M == P ? 1 : 0;
        } else if (M == 1) {
            result = process2(M + 1, K - 1, N, P, cache);
        } else if (M == N) {
            result = process2(M - 1, K - 1, N, P, cache);
        } else {
            result = process2(M + 1, K - 1, N, P, cache) + process2(M - 1, K - 1, N, P, cache);
        }
        cache[M][K] = result;
        return result;
    }

    // M:当前位置,K:必须走K步,N:有N个数,P:目标位置
    public static int robotWalk3(int M, int K, int N, int P) {
        if (N < 2 || M < 1 || M > N || P > N || P < 1) {
            return -1;
        }
        int[][] table = new int[N + 1][K + 1];
        table[P][0] = 1;
        for (int col = 1; col <= K; col++) {
            table[1][col] = table[2][col - 1];
            for (int row = 2; row < N; row++) {
                table[row][col] = table[row - 1][col - 1] + table[row + 1][col - 1];
            }
            table[N][col] = table[N - 1][col - 1];
        }
        return table[M][K];
    }

    public static void main(String[] args) {
        System.out.println(robotWalk1(2, 4, 5, 4));
        System.out.println(robotWalk2(2, 4, 5, 4));
        System.out.println(robotWalk3(2, 4, 5, 4));
    }
}