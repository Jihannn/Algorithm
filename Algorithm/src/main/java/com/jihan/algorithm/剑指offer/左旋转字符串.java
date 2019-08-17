package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/18
 *
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 */
public class 左旋转字符串 {
    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() <= 0 || n > str.length()){
            return "";
        }
        StringBuffer sb = new StringBuffer(str);
        sb.append(str.substring(0,n));
        return sb.substring(n,sb.length());
    }

    public String LeftRotateString2(String str,int n) {
        if(str == null || str.length() <= 0 || n > str.length()){
            return "";
        }
        char[] words = str.toCharArray();
        swap(words,0,n-1);
        swap(words,n,str.length()-1);
        swap(words,0,str.length()-1);
        return new String(words);
    }

    private void swap(char[] words,int head,int tail){
        char temp = 0;
        while(head < tail){
            temp = words[tail];
            words[tail] = words[head];
            words[head] = temp;
            --tail;
            ++head;
        }
    }
}
