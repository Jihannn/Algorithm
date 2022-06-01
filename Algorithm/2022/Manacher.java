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
        // # newArr：(index - 1) / 2
        char[] newArr = getNewArr(str);
        // radiusArr
        int[] radiusArr = new int[str.length() * 2 + 1];
        int c = -1;
        int r = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < newArr.length; i++) {
            // i < R 对应i`=> (c * 2 - i)
            // i.l范围在L,R内
            // i.l范围超出L
            radiusArr[i] = i < r ? Math.min(radiusArr[c * 2 - i], r - i) : 1;
            // i.l范围等于L
            // i > R 扩散
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