/*
 * @Author: Jihan
 * @Date: 2022-07-22 22:19:40
 * @Description: https://leetcode.cn/problems/implement-strstr/
 */
public class _028_ImplementStrStr {
    public int strStr1(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        if (haystack == null || haystack.isEmpty()) {
            return -1;
        }
        return haystack.indexOf(needle);
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        if (haystack == null || haystack.isEmpty()) {
            return -1;
        }
        char[] str1 = haystack.toCharArray();
        char[] str2 = needle.toCharArray();
        int[] preIndexArr = getPreIndexArr(str2);
        int c1 = 0;
        int c2 = 0;
        while (c1 != str1.length && c2 != str2.length) {
            if (str1[c1] == str2[c2]) {
                c1++;
                c2++;
            } else if (preIndexArr[c2] != -1) {
                c2 = preIndexArr[c2];
            } else {
                c1++;
            }
        }
        return c2 == str2.length ? c1 - c2 : -1;
    }

    private int[] getPreIndexArr(char[] str) {
        if (str.length < 2) {
            return new int[] { -1 };
        }
        int[] arr = new int[str.length];
        arr[0] = -1;
        arr[1] = 0;
        int cur = 2;
        int pre = 0;
        while (cur != arr.length) {
            if (str[cur - 1] == str[pre]) {
                arr[cur++] = ++pre;
            } else if (arr[pre] != -1) {
                pre = arr[pre];
            } else {
                arr[cur++] = 0;
            }
        }
        return arr;
    }
}