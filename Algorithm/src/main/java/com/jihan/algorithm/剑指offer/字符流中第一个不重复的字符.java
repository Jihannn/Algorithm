package com.jihan.algorithm.剑指offer;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author Jihan
 * @date 2019/8/22
 * <p>
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 */
public class 字符流中第一个不重复的字符 {

    private static class one {
        private LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        //Insert one char from stringstream
        public void Insert(char ch) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        //return the first appearence once char in current stringstream
        public char FirstAppearingOnce() {
            for (char c : map.keySet()) {
                if (map.get(c) == 1) {
                    return c;
                }
            }
            return '#';
        }
    }

    private static class two {
        int[] code = new int[128];
        LinkedList<Character> queue = new LinkedList<>();

        //Insert one char from stringstream
        public void Insert(char ch) {
            if (code[ch - ' '] == 0) {
                queue.add(ch);
            }

            ++code[ch - ' '];
        }

        //return the first appearence once char in current stringstream
        public char FirstAppearingOnce() {
            while (!queue.isEmpty() && code[queue.getFirst() - ' '] >= 2) {
                queue.removeFirst();
            }
            if (!queue.isEmpty()) {
                return queue.getFirst();
            }
            return '#';
        }
    }

}
