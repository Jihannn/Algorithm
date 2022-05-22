/*
 * @Author: Jihan
 * @Date: 2022-05-20 09:34:46
 * @Description: 
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
package Recursion;

public class KillMonster {
    public static double force(int N, int M, int K) {
        if (N < 0 || K < 0 || M < 0) {
            return -1;
        }
        return process(N, K, M) / Math.pow(M + 1, K);
    }

    private static long process(int hp, int times, int damage) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(damage + 1, times);
        }
        int ways = 0;
        for (int i = 0; i <= damage; i++) {
            ways += process(hp - i, times - 1, damage);
        }
        return ways;
    }

    public static double dp(int N, int M, int K) {
        if (N < 0 || M < 0 || K < 0) {
            return -1;
        }
        double[][] dp = new double[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - M - 1 >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - M - 1];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = force(N, M, K);
            double ans2 = dp(N, M, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}