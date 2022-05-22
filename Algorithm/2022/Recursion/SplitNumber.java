/*
 * @Author: Jihan
 * @Date: 2022-05-22 18:15:03
 * @Description: 
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
package Recursion;

public class SplitNumber {
    public static int splitNumber(int n) {
        if (n < 1) {
            return -1;
        }
        return process(1, n);
    }

    private static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }

    public static int splitNumberDP(int n) {
        if (n < 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int i = pre; i <= rest; i++) {
                    ways += dp[i][rest - i];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int splitNumberDP2(int n) {
        if (n < 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int n = 32;
        System.out.println(splitNumber(n));
        System.out.println(splitNumberDP(n));
        System.out.println(splitNumberDP2(n));
    }
}
