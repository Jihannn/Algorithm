import java.util.HashMap;

/*
 * @Author: Jihan
 * @Date: 2022-07-19 15:14:05
 * @Description: https://leetcode.cn/problems/roman-to-integer/
 */
public class _013_RomanToInteger {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] str = s.toCharArray();
        int rtn = 0;
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (i + 1 < str.length && (c == 'I' || c == 'X' || c == 'C')
                    && map.get(str[i]) < map.get(str[i + 1])) {
                rtn += map.get(str[i + 1]) - map.get(str[i]);
                i++;
                continue;
            }
            rtn += map.get(c);
        }
        return rtn;
    }
}
