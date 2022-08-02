import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-08-02 21:42:10
 * @Description: https://leetcode.cn/problems/valid-anagram/
 */

public class _242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return s == null && t == null;
        }
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                break;
            } else {
                int num = map.get(c);
                if (num - 1 == 0) {
                    map.remove(c);
                } else {
                    map.put(c, num - 1);
                }
            }
        }
        return map.isEmpty();
    }
}
