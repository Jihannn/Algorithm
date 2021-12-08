package Other.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
 * @Author: Jihan
 * 
 * @Date: 2021-12-08 17:16:56
 * 
 * @Description:最小词典，多个字符串怎么排序，使得它的字典序最小
 */
public class LowestLexicography {

    private static class LexicographyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String sort(String[] words) {
        Arrays.sort(words, new LexicographyComparator());
        String result = "";
        for (String w : words) {
            result += w;
        }
        return result;
    }
}