/*
 * @Author: Jihan
 * @Date: 2022-05-17 08:37:44
 * @Description: https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 给你一个字符串s，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 */
package Recursion;

public class LongestPalindromeSubseq {
    public static int findSub(String s) {
        if (s == null) {
            return -1;
        }
        return process(0, s.length() - 1, s.toCharArray());
    }

    private static int process(int i1, int i2, char[] str) {
        if (i1 > i2) {
            return 0;
        }
        if (i1 == i2) {
            return 1;
        }
        int p1 = process(i1 + 1, i2 - 1, str) + (str[i1] == str[i2] ? 2 : 0);
        int p2 = process(i1 + 1, i2, str);
        int p3 = process(i1, i2 - 1, str);
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int finbSub(String s) {
        if (s == null) {
            return -1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        dp[N - 1][N - 1] = 1;
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                int p1 = dp[row + 1][col];
                int p2 = dp[row][col - 1];
                int p3 = dp[row + 1][col - 1] + (str[row] == str[col] ? 2 : 0);
                dp[row][col] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[0][N - 1];
    }
}