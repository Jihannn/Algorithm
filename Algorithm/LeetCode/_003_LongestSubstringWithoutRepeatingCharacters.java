/*
 * @Author: Jihan
 * @Date: 2022-07-03 20:09:43
 * @Description: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class _003_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] strArr = s.toCharArray();
        // 字符编码，记录最后一次出现的位置
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        // 前一个字符所能达到的最左侧
        int pre = -1;
        int rtn = 0;
        for (int i = 0; i < strArr.length; i++) {
            // 只能到离i最近的位置
            pre = Math.max(pre, map[strArr[i]]);
            rtn = Math.max(rtn, i - pre);
            map[strArr[i]] = i;
        }
        return rtn;
    }
}