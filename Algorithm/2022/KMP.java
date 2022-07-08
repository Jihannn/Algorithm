/*
 * @Author: Jihan
 * @Date: 2022-05-31 08:30:18
 * @Description: KMP算法
 */
public class KMP {
    public static int kmp(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 得到每个位置前一位的前缀数和后缀数相同的最长数
        int[] preIndexArr = getPreIndexArr(str2);
        int c1 = 0;
        int c2 = 0;
        while (c1 < s1.length() && c2 < s2.length()) {
            if (str1[c1] == str2[c2]) {
                c1++;
                c2++;
            } else if (preIndexArr[c2] == -1) {
                c1++;
            } else {
                c2 = preIndexArr[c2];
            }
        }
        return c2 == s2.length() ? c1 - c2 : -1;
    }

    private static int[] getPreIndexArr(char[] s) {
        if (s.length < 2) {
            return new int[] { -1 };
        }
        int[] preIndexArr = new int[s.length];
        preIndexArr[0] = -1;
        preIndexArr[1] = 0;
        int cur = 2;
        int pre = 0;
        while (cur < s.length) {
            if (s[cur - 1] == s[pre]) {
                preIndexArr[cur++] = ++pre;
            } else if (pre > 0) {
                pre = preIndexArr[pre];
            } else {
                preIndexArr[cur++] = 0;
            }
        }
        return preIndexArr;
    }

    public static void main(String[] args) {
        String s1 = "jsklfjaosoaugjihansd";
        String s2 = "jihan";
        System.out.println(kmp(s1, s2));
    }
}