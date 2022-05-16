/*
 * @Author: Jihan
 * @Date: 2022-05-16 08:37:58
 * @Description: 
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 */
package Recursion;

public class LongestCommonSub {
    public static int commonSub1(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        return process1(0, 0, str1.toCharArray(), str2.toCharArray());
    }

    private static int process1(int index1, int index2, char[] str1, char[] str2) {
        if (index1 == str1.length || index2 == str2.length) {
            return 0;
        }
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        if (str1[index1] == str2[index2]) {
            p1 = 1 + process1(index1 + 1, index2 + 1, str1, str2);
        } else {
            p2 = process1(index1, index2 + 1, str1, str2);
            p3 = process1(index1 + 1, index2, str1, str2);
        }
        return Math.max(Math.max(p1, p2), p3);
    }

    public static int commonSub2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        return process2(str1.length() - 1, str2.length() - 1, str1.toCharArray(), str2.toCharArray());
    }

    private static int process2(int i1, int i2, char[] str1, char[] str2) {
        if (i1 == 0 && i2 == 0) {
            return str1[i1] == str2[i2] ? 1 : 0;
        } else if (i1 == 0) {
            return str1[i1] == str2[i2] ? 1 : process2(i1, i2 - 1, str1, str2);
        } else if (i2 == 0) {
            return str1[i1] == str2[i2] ? 1 : process2(i1 - 1, i2, str1, str2);
        } else {
            int p1 = 0;
            int p2 = 0;
            int p3 = 0;
            if (str1[i1] != str2[i2]) {
                p1 = process2(i1 - 1, i2, str1, str2);
                p2 = process2(i1, i2 - 1, str1, str2);
            } else {
                p3 = 1 + process2(i1 - 1, i2 - 1, str1, str2);
            }
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int commonSub3(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int[][] dp = new int[str1.length()][str2.length()];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < str1.length(); i++) {
            dp[i][0] = c1[i] == c2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < str2.length(); i++) {
            dp[0][i] = c1[0] == c2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = c1[i] == c2[j] ? dp[i - 1][j - 1] + 1 : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[str1.length() - 1][str2.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(commonSub1("a12b3c456dpppppp7", "1ef23ghi4j567k"));
        System.out.println(commonSub2("a12b3c456dpppppp7", "1ef23ghi4j567k"));
        System.out.println(commonSub3("a12b3c456dpppppp7", "1ef23ghi4j567k"));
    }
}
