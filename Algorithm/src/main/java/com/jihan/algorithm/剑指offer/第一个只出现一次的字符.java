package com.jihan.algorithm.剑指offer;

import java.util.HashMap;

/**
 * @author Jihan
 * @date 2019/8/11
 *
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class 第一个只出现一次的字符 {

    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        char c;
        int count;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (map.containsKey(c)) {
                count = map.get(c);
                map.put(c, count + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int j = 0; j < str.length(); j++) {
            c = str.charAt(j);
            count = map.get(c);
            if (count == 1) {
                return j;
            }
        }

        return -1;
    }
}
