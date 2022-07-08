/*
 * @Author: Jihan
 * @Date: 2022-06-01 08:35:17
 * @Description: 最大回文子串
 */
public class Manacher {
    public static int manacher(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        // 1234 变为 #1#2#3#4# 避免偶数回文找不到
        char[] newArr = getNewArr(str);
        // 每个数的回文半径
        int[] radiusArr = new int[newArr.length];
        int c = -1;
        int r = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < newArr.length; i++) {
            // 1.i在r外侧 正常求解
            // 2.i在r内侧分三种情况
            //   1.i的回文半径不到r
            //   2.i的回文半径刚好到r
            //   3.i的回文半径超过r

            // 求至少回文半径长度
            // c * 2 - i 是 i的对称点
            radiusArr[i] = i < r ? Math.min(radiusArr[c * 2 - i], r - i) : 1;

            while (radiusArr[i] + i < newArr.length && i - radiusArr[i] > -1) {
                if (newArr[radiusArr[i] + i] == newArr[i - radiusArr[i]]) {
                    radiusArr[i]++;
                } else {
                    break;
                }
            }
            if (radiusArr[i] + i > r) {
                r = radiusArr[i] + i;
                c = i;
            }
            max = Math.max(radiusArr[i], max);
        }
        // max - 1即为原数组的回文子串长度
        return max - 1;
    }

    private static char[] getNewArr(String str) {
        char[] s = str.toCharArray();
        char[] rtn = new char[s.length * 2 + 1];
        rtn[0] = '#';
        for (int i = 1; i < rtn.length; i++) {
            rtn[i] = i % 2 == 0 ? '#' : s[i / 2];
        }
        return rtn;
    }
}