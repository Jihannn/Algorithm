/*
 * @Author: Jihan
 * @Date: 2022-07-08 17:33:03
 * @Description: https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class _005_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 1234 变为 #1#2#3#4# 避免偶数回文找不到
        char[] manacherArr = getManacherArr(s);
        // 每个数的回文半径
        int[] radiusArr = new int[manacherArr.length];
        int c = -1;
        int r = -1;
        int max = Integer.MIN_VALUE;
        int end = 0;
        for (int i = 0; i < manacherArr.length; i++) {
            // 存在两种情况
            // 1.i在r外侧 正常求解
            // 2.i在r内侧分三种情况
            // 1.i的回文半径不到r
            // 2.i的回文半径刚好到r
            // 3.i的回文半径超过r

            // 求至少回文半径长度
            // c * 2 - i 是 i的对称点
            radiusArr[i] = i < r ? Math.min(radiusArr[2 * c - i], r - i) : 1;

            // 如果 2.1 2.2 进入则直接break 减少判断代码
            while (i - radiusArr[i] > -1 && i + radiusArr[i] < manacherArr.length) {
                if (manacherArr[i - radiusArr[i]] == manacherArr[i + radiusArr[i]]) {
                    radiusArr[i]++;
                } else {
                    break;
                }
            }

            if (radiusArr[i] + i > r) {
                r = radiusArr[i] + i;
                c = i;
            }

            if (radiusArr[i] > max) {
                max = radiusArr[i];
                // 因为r表示不能到达的位置
                // r-1为'#' r-2为实际字符位置
                // end 表示原数组最长回文字符串的最后一个字符下标
                end = (r - 2) / 2;
            }
        }
        // substring前闭后开
        // max - 1 为原字符串实际回文长度
        return s.substring(end - (max - 1) + 1, end + 1);
    }

    private char[] getManacherArr(String s) {
        char[] originArr = s.toCharArray();
        char[] manacherArr = new char[s.length() * 2 + 1];
        manacherArr[0] = '#';
        for (int i = 1; i < manacherArr.length; i++) {
            manacherArr[i] = (i & 1) == 0 ? '#' : originArr[i / 2];
        }
        return manacherArr;
    }
}
