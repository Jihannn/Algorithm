package Greed;

import java.util.Arrays;
import java.util.Comparator;

/*
 * @Author: Jihan
 * @Date: 2022-05-07 17:05:24
 * @Description: 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
 */
public class FindMinimumSubString {
    public static class StrComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String getMinimumSubString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new StrComparator());
        String rtn = "";
        for (String str : strs) {
            rtn += str;
        }
        return rtn;
    }
}