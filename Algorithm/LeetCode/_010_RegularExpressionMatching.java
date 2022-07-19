/*
 * @Author: Jihan
 * @Date: 2022-07-08 21:00:32
 * @Description: https://leetcode.cn/problems/regular-expression-matching/
 */
public class _010_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return true;
        }
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        return isValid(sArr, pArr) && process(sArr, 0, pArr, 0);
    }

    private boolean isValid(char[] sArr, char[] pArr) {
        if (sArr.length != 0 && pArr.length == 0) {
            return false;
        }
        // 不能开头*
        if (pArr[0] == '*') {
            return false;
        }
        // 不能连续*
        int starIndex = -1;
        for (int i = 1; i < pArr.length; i++) {
            if (pArr[i] == '*') {
                if (starIndex == i - 1) {
                    return false;
                }
                starIndex = i;
            }
        }
        return true;
    }

    private boolean process(char[] s, int sIndex, char[] p, int pIndex) {
        if (sIndex == s.length) {
            if (pIndex == p.length) {
                return true;
            }
            // abcd
            // abcde*b*
            if (pIndex + 1 < p.length && p[pIndex + 1] == '*') {
                return process(s, sIndex, p, pIndex + 2);
            }
            return false;
        } else {
            if (pIndex == p.length) {
                return false;
            }
        }
        // 结尾或者 p[pIndex + 1] != '*'
        if (pIndex + 1 >= p.length || p[pIndex + 1] != '*') {
            return (s[sIndex] == p[pIndex] || p[pIndex] == '.') && process(s, sIndex + 1, p, pIndex + 1);
        }
        // 非结尾且有 p[pIndex + 1] == '*'
        // 匹配0个*
        if (process(s, sIndex, p, pIndex + 2)) {
            return true;
        }
        // 匹配多个*
        while (sIndex < s.length && (s[sIndex] == p[pIndex] || p[pIndex] == '.')) {
            if (process(s, sIndex + 1, p, pIndex + 2)) {
                return true;
            }
            sIndex++;
        }
        return false;
    }

    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return true;
        }
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        for (int row = 0; row <= s.length(); row++) {
            for (int col = 0; col <= p.length(); col++) {
                dp[row][col] = -1;
            }
        }
        return isValid(sArr, pArr) && process2(sArr, 0, pArr, 0, dp);
    }

    private boolean process2(char[] s, int sIndex, char[] p, int pIndex, int[][] dp) {
        if (dp[sIndex][pIndex] != -1) {
            return dp[sIndex][pIndex] == 1;
        }
        if (sIndex == s.length) {
            if (pIndex == p.length) {
                dp[sIndex][pIndex] = 1;
                return true;
            }
            // abcd
            // abcde*b*
            if (pIndex + 1 < p.length && p[pIndex + 1] == '*') {
                dp[sIndex][pIndex] = process2(s, sIndex, p, pIndex + 2, dp) ? 1 : 0;
                return dp[sIndex][pIndex] == 1;
            }
            dp[sIndex][pIndex] = 0;
            return false;
        } else {
            if (pIndex == p.length) {
                dp[sIndex][pIndex] = 0;
                return false;
            }
        }
        // 结尾或者 p[pIndex + 1] != '*'
        if (pIndex + 1 >= p.length || p[pIndex + 1] != '*') {
            dp[sIndex][pIndex] = (s[sIndex] == p[pIndex] || p[pIndex] == '.')
                    && process2(s, sIndex + 1, p, pIndex + 1, dp)
                            ? 1
                            : 0;
            return dp[sIndex][pIndex] == 1;
        }
        // 非结尾且有 p[pIndex + 1] == '*'
        // 匹配0个*
        if (process2(s, sIndex, p, pIndex + 2, dp)) {
            dp[sIndex][pIndex] = 1;
            return true;
        }
        // 匹配多个*
        while (sIndex < s.length && (s[sIndex] == p[pIndex] || p[pIndex] == '.')) {
            if (process2(s, sIndex + 1, p, pIndex + 2, dp)) {
                dp[sIndex][pIndex] = 1;
                return true;
            }
            sIndex++;
        }
        dp[sIndex][pIndex] = 0;
        return false;
    }
}
