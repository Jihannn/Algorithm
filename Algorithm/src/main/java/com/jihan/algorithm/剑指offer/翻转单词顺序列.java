package com.jihan.algorithm.剑指offer;


/**
 * @author Jihan
 * @date 2019/8/17
 *
 * 例如，“student. a am I”
 * 正确的句子应该是“I am a student.”
 */
public class 翻转单词顺序列 {
    public String ReverseSentence(String str) {
        if(str == null || str.length() <= 0){
            return "";
        }
        char[] words = str.toCharArray();
        int length = str.length();
        int headIndex = 0;
        int tailIndex = length - 1;
        swap(words,headIndex,tailIndex);

        int cur = 0;
        while(cur < length){
            if(cur < length && words[cur] == ' '){
                ++cur;
            }

            headIndex = tailIndex = cur;
            while(cur < length && words[cur] != ' '){
                ++cur;
                ++tailIndex;
            }
            swap(words,headIndex,tailIndex-1);
        }
        return String.valueOf(words);
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
