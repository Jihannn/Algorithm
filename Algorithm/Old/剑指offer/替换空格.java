package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/7/29
 *
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class 替换空格 {

    public String replaceSpace(StringBuffer str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //在原数组中替换，因为从前往后替换时间复杂度为O(n^2)，所以从后往前替换。

    public String replaceSpace2(StringBuffer str) {
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
            }
        }

        int newLength = str.length() + spaceNum * 2;
        int oldIndex = str.length() - 1;
        int newIndex = newLength - 1;

        str.setLength(newLength);
        while (oldIndex >= 0 && newIndex >= oldIndex) {
            char c = str.charAt(oldIndex);
            if (c == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, c);
            }
            oldIndex--;
        }

        return str.toString();
    }
}
