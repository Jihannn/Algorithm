/*
 * @Author: Jihan
 * @Date: 2022-05-15 12:09:16
 * @Description: 
 * 背包问题
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回能装下的最大价值
 */
package Recursion;

public class Knapsack {
    public static int knapsack1(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || bag < 0) {
            return -1;
        }
        return process1(0, weights, values, bag);
    }

    private static int process1(int index, int[] weights, int[] values, int bag) {
        if (bag < 0 || index == weights.length) {
            return 0;
        }
        int p1 = 0;
        if (bag - weights[index] >= 0) {
            p1 = values[index] + process1(index + 1, weights, values, bag - weights[index]);
        }
        int p2 = process1(index + 1, weights, values, bag);
        return Math.max(p1, p2);
    }

    public static int dp(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || bag < 0) {
            return -1;
        }
        int N = weights.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - weights[index] >= 0) {
                    p2 = values[index] + dp[index + 1][rest - weights[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(knapsack1(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
