package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by Jihan on 2019/8/7
 *
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class 字符串的排序 {

    public class Solution {

        ArrayList<String> list = new ArrayList<>();

        public ArrayList<String> Permutation(String str) {
            if(str == null || str.length() == 0){
                return list;
            }
            judge(str.toCharArray(),0);
            Collections.sort(list);
            return list;
        }

        private void judge(char[] c,int cur){
            if(cur == c.length - 1){
                list.add(String.valueOf(c));
                return;
            }
            HashSet<Character> set = new HashSet<>();
            for(int j = cur; j < c.length; j++){
                if(!set.contains(c[j])){
                    set.add(c[j]);
                    swap(c,cur,j);
                    judge(c,cur+1);
                    swap(c,j,cur);
                }
            }
        }

        private void swap(char[] c,int pre,int next){
            char temp = c[pre];
            c[pre] = c[next];
            c[next] = temp;
        }
    }
}
