package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/22
 * <p>
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * <p>
 * 12e说明e的后面必须有数字，不能有两个e
 * +-5说明符号位要么出现一次在首位，要么出现一次在e的后一位，其他地方都不能有
 * 12e4.3说明e的后面不能有小数，1.2.3说明不能有两个小数点
 * 1a3.14说明不能有其他的非法字符，比如这里的a
 */
public class 表示数值的字符串 {
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        boolean hasSingle = false;
        boolean hasE = false;
        boolean hasPoint = false;

        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c == 'E' || c == 'e') {
                if (hasE || i == str.length - 1) {
                    return false;
                }
                hasE = true;
            } else if (c == '+' || c == '-') {
                if (hasSingle && !(str[i - 1] == 'E' || str[i - 1] == 'e')) {
                    return false;
                }
                if (!hasSingle && i != 0 && !(str[i - 1] == 'E' || str[i - 1] == 'e')) {
                    return false;
                }
                hasSingle = true;
            } else if (c == '.') {
                if (hasE || hasPoint) {
                    return false;
                }
                hasPoint = true;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
