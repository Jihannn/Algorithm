/*
 * @Author: Jihan
 * @Date: 2022-05-12 11:48:39
 * @Description: 1.打印一个字符串的全部子序列 2.打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 */
package Recursion;

import java.util.ArrayList;
import java.util.HashSet;

public class PrintAllSubsquences {
    public static void printAllSub(String str) {
        if (str == null) {
            return;
        }
        char[] c = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        rec(c, 0, "", result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void rec(char[] c, int index, String path, ArrayList<String> result) {
        if (index == c.length) {
            result.add(path);
        } else {
            rec(c, index + 1, path + c[index], result);
            rec(c, index + 1, path, result);
        }
    }

    public static void printAllSub2(String str) {
        if (str == null) {
            return;
        }
        char[] c = str.toCharArray();
        HashSet<String> result = new HashSet<>();
        rec2(c, 0, "", result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void rec2(char[] c, int index, String path, HashSet<String> result) {
        if (index == c.length) {
            result.add(path);
        } else {
            rec2(c, index + 1, path + c[index], result);
            rec2(c, index + 1, path, result);
        }
    }

    public static void main(String[] args) {
        printAllSub("abcb");
        System.out.println("=====");
        printAllSub2("abcb");
    }
}
