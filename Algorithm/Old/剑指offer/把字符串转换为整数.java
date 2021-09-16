package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/20
 *
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。数值为0或者字符串不是一个合法的数值则返回0。
 */
public class 把字符串转换为整数 {
    public int StrToInt(String str) {
        if (str == null || str.trim().equals("")) {
            return 0;
        }
        int length = str.length();
        int start = 0;
        int symbol = 1;
        char c = str.charAt(0);
        if (c == '+') {
            start = 1;
            symbol = 1;
        } else if (c == '-') {
            start = 1;
            symbol = -1;
        }
        int result = 0;
        for (int i = start; i < length; i++) {
            c = str.charAt(i);
            if (c < '0' || c > '9') {
                return 0;
            }
            int sum = result * 10 + (int) (c - '0');

            if ((sum - (int) (c - '0')) / 10 != result) {
                return 0;
            }

            result = result * 10 + (int) (c - '0');
        }
        return symbol * result;
    }
}