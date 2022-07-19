/*
 * @Author: Jihan
 * @Date: 2022-07-19 15:37:27
 * @Description: https://leetcode.cn/problems/longest-common-prefix/
 */
public class _014_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        String s = strs[0];
        int prefixIndex = s.length();
        for (String str : strs) {
            int cur = 0;
            while (cur < prefixIndex && cur < str.length()) {
                if (str.charAt(cur) != s.charAt(cur)) {
                    break;
                }
                cur++;
            }
            prefixIndex = cur;
            if (prefixIndex == 0) {
                return "";
            }
        }
        return s.substring(0, prefixIndex);
    }
}
