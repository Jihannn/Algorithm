/*
 * @Author: Jihan
 * @Date: 2022-05-15 18:20:16
 * @Description: https://leetcode.cn/problems/stickers-to-spell-word/
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
package Recursion;

import java.util.HashMap;

public class StickersToSpellWord {
    public static int minStickers(String[] stickers, String target) {
        if (target == null || stickers.length < 1) {
            return -1;
        }
        int result = process(target, stickers);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int process(String target, String[] stickers) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String s : stickers) {
            String rest = minus(target, s);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(rest, stickers));
            }
        }
        // 第一个sticker没算
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String str, String sticker) {
        char[] strWords = new char[26];
        for (char c : str.toCharArray()) {
            strWords[c - 'a']++;
        }
        for (char c : sticker.toCharArray()) {
            if (strWords[c - 'a'] != 0) {
                strWords[c - 'a']--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strWords.length; i++) {
            while (strWords[i] != 0) {
                sb.append((char) ('a' + i));
                strWords[i]--;
            }
        }
        return sb.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        if (stickers == null || target == null) {
            return 0;
        }
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            for (char c : stickers[i].toCharArray()) {
                counts[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int result = process2(counts, target, dp);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int process2(int[][] stickers, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        char[] tC = target.toCharArray();
        int[] tCounts = new int[26];
        for (char c : tC) {
            tCounts[c - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            if (sticker[tC[0] - 'a'] > 0) {
                StringBuilder rest = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tCounts[j] > 0) {
                        int num = tCounts[j] - sticker[j];
                        while (num > 0) {
                            rest.append((char) ('a' + j));
                            num--;
                        }
                    }
                }
                min = Math.min(min, process2(stickers, rest.toString(), dp));
            }
        }
        min = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, min);
        return min;
    }
}