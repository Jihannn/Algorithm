/*
 * @Author: Jihan
 * @Date: 2022-05-12 12:03:54
 * @Description: 1.打印一个字符串的全部排列 2.打印一个字符串的全部排列，要求不要出现重复的排列
 */
package Recursion;

import java.util.ArrayList;

public class PrintAllPermutations {
    public static void printAllPer(String str) {
        if (str == null) {
            return;
        }
        ArrayList<String> result = new ArrayList<>();
        char[] c = str.toCharArray();
        rec(c, 0, result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void rec(char[] str, int index, ArrayList<String> result) {
        if (index == str.length) {
            result.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, i, index);
                rec(str, index + 1, result);
                swap(str, i, index);
            }
        }
    }

    public static void printAllPer2(String str) {
        if (str == null) {
            return;
        }
        ArrayList<String> result = new ArrayList<>();
        char[] c = str.toCharArray();
        rec2(c, 0, result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void rec2(char[] str, int index, ArrayList<String> result) {
        if (index == str.length) {
            result.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if(!visited[str[i]]){
                    visited[str[i]] = true;
                    swap(str, i, index);
                    rec2(str, index + 1, result);
                    swap(str, i, index);
                }
            }
        }
    }

    private static void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void main(String[] args) {
        printAllPer("abb");
        System.out.println("===");
        printAllPer2("abb");
    }
}